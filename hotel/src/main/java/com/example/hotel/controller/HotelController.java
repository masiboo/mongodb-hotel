package com.example.hotel.controller;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public Hotel save(@RequestBody Hotel hotel){
        return hotelService.save(hotel);
    }



}