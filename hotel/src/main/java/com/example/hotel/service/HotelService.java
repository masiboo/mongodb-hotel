package com.example.hotel.service;

import com.example.hotel.model.Hotel;
import com.example.hotel.repo.HotelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final MongoTemplate mongoTemplate;

    public HotelService(HotelRepository hotelRepository, MongoTemplate mongoTemplate) {
        this.hotelRepository = hotelRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Hotel findById(String id) {
        var optHotel = hotelRepository.findById(id);
        return optHotel.orElse(null);
    }

    public List<Hotel> findByPricePerNightLessThan(int maxPrice) {
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    public List<Hotel> findCityPrice(int maxPrice, String city) {
        Query query = new Query()
                .addCriteria(Criteria.where("address.city").is(city)
                        .andOperator(Criteria.where("pricePerNight").lte(maxPrice)))
                .with(Sort.by(Sort.Order.desc("pricePerNight")))
                .limit(4);

        List<Hotel> result = mongoTemplate.find(query, Hotel.class);
        return result;
    }

    public List<Hotel> findPriceRating(int price, int rating) {
        Query query = new Query()
                .addCriteria(Criteria.where("pricePerNight").lte(price))
                .addCriteria(Criteria.where("reviews.rating").gte(rating))
                .with(Sort.by(Sort.Order.desc("pricePerNight")));
        var result = mongoTemplate.find(query, Hotel.class);
        return result;
    }

    public List<Hotel> findAll() {
        return mongoTemplate.findAll(Hotel.class);
    }

    public Hotel save(Hotel hotel) {
        return mongoTemplate.save(hotel);
    }

    public List<Hotel> noReview() {
        Query query = new Query()
                .addCriteria(Criteria.where("reviews.userName").exists(false));

        var result = mongoTemplate.find(query, Hotel.class);
        return result;
    }

    public List<Hotel> unapproved() {
        Query query = new Query()
                .addCriteria(Criteria.where("reviews.approved").is(false));

        var result = mongoTemplate.find(query, Hotel.class);
        return result;
    }

    public List<Hotel> notEqual(String name) {
        Query query = new Query()
                .addCriteria(Criteria.where("name").ne(name));

        var result = mongoTemplate.find(query, Hotel.class);
        return result;
    }

    public List<Hotel> nameEqual(String name) {
        Query query = new Query()
                .addCriteria(Criteria.where("name").is(name));

        var result = mongoTemplate.find(query, Hotel.class);
        return result;
    }

    public List<Hotel> findNameStartWithM() {
        return hotelRepository.findNameStartWithM();
    }

    public List<Hotel> findNameStartWithE() {
        return hotelRepository.findNameStartWithE();
    }

    public List<Hotel> deleteHotelWithE() {
        return hotelRepository.deleteNameWithE();
    }

    public void deleteAll(){
        mongoTemplate.dropCollection("Hotels");
    }

    public Page<Hotel> getPage(int page, int size){
        final Pageable pageableRequest = PageRequest.of(page, size);
        var pageHotel = hotelRepository.findAll(pageableRequest);
        return pageHotel;
    }

    public List<Hotel> findByPricePerNightBetween(int start, int end){
        var res = hotelRepository.findByPricePerNightBetween(start, end);
        return res;
    }
}
