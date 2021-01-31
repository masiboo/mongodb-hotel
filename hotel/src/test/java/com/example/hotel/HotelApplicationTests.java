package com.example.hotel;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class HotelApplicationTests {

	@Autowired
	private static HotelService hotelService;

	private static final int NUMBER_OF_COUNTRIES = 6;
	private static final String HOTEL_NAME1 = "hotel1";
	private static final String HOTEL_NAME2 = "hotel2";

	@BeforeEach
	public  void init() {
		hotelService.save(new Hotel(HOTEL_NAME1, 200, null,null  ));
		hotelService.save(new Hotel(HOTEL_NAME2, 100, null,null  ));
	}

	@Test
	public void countAllCountries() {
		var hotelList = hotelService.findAll();
		assertEquals(NUMBER_OF_COUNTRIES, hotelList.size());
	}

	@Test
	public void testNameEqual() {
		var hotels = hotelService.nameEqual(HOTEL_NAME1);
		assertFalse(hotels.isEmpty());
	}

	@Test
	public void setsIdOnSave() {

		Hotel nigeria = hotelService.save(new Hotel("Nigeria", 186, null, null));
		assertThat(nigeria.getId()).isNotNull();
	}

}
