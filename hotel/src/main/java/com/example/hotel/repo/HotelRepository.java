package com.example.hotel.repo;

import com.example.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {

    Optional<Hotel> findById(String id);

    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    @Query(value = "{address.city:?0}")
    List<Hotel> findByCity(String city);

    @Query("{'name': {$regex: '^M.*'} }")
    public List<Hotel> findNameStartWithM();

    @Query("{'name': {$regex: '^E.*'} }")
    public List<Hotel> findNameStartWithE();

    @Query(value = "{'name': {$regex: '^E.*'} }", delete = true)
    public List<Hotel> deleteNameWithE();

}
