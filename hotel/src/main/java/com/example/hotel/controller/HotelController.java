package com.example.hotel.controller;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public List<Hotel> findAll(){
        return hotelService.findAll();
    }

    @GetMapping("/maxPrice")
    public List<Hotel> findByPricePerNightLessThan(@RequestParam int maxPrice){
        return hotelService.findByPricePerNightLessThan(maxPrice);
    }

    @GetMapping("/priceRating")
    public List<Hotel> findPriceRating(@RequestParam int price, @RequestParam int rating){
        return hotelService.findPriceRating(price, rating);
    }

    @GetMapping("/cityPrice")
    public List<Hotel> findCityPrice(@RequestParam int maxPrice, @RequestParam String city){
        return hotelService.findCityPrice(maxPrice, city);
    }

    @GetMapping("/noReview")
    public List<Hotel> noReview(){
        return hotelService.noReview();
    }

    @GetMapping("/unapproved")
    public List<Hotel> unapproved(){
        return hotelService.unapproved();
    }

    @GetMapping("/ne")
    public List<Hotel> notEqual(@RequestParam String name){
        return hotelService.notEqual(name);
    }

    @GetMapping("/eq")
    public List<Hotel> nameEqual(@RequestParam String name){
        return hotelService.nameEqual(name);
    }

    @GetMapping("/startm")
    public List<Hotel> findHotelStartWithM(){
        return hotelService.findNameStartWithM();
    }

    @GetMapping("/starte")
    public List<Hotel> findHotelStartWithE(){
        return hotelService.findNameStartWithE();
    }

    @GetMapping("/page")
    public Page<Hotel> getPage(int page, int size ){
        return hotelService.getPage(page, size);
    }

    @GetMapping("/price-range")
    public List<Hotel> findByPricePerNightBetween(@RequestParam int start, @RequestParam int end ){
        return hotelService.findByPricePerNightBetween(start, end);
    }

    @DeleteMapping("/dele")
    public List<Hotel> deleteHotelWithE(){
        return hotelService.deleteHotelWithE();
    }



    @PostMapping("/save")
    public Hotel save(@RequestBody Hotel hotel){
        return hotelService.save(hotel);
    }

    @DeleteMapping("/delAll")
    public void deleteAll(){
        hotelService.deleteAll();
    }



}
