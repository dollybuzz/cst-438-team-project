package cst438;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438.domain.*;
import cst438.service.CityService;
 
@SpringBootTest
public class CityServiceTest {
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCitiesFound() throws Exception {
		City testCity = new City(3799, "San Diego", "USA", "California", 1223400);
		List<City> testCities = new ArrayList<City>(Arrays.asList(testCity));
		
		given(cityRepository.findAllByOrderByNameAsc()).willReturn(testCities);

		assertEquals("Cities lists are equal", testCities, cityService.getCities());
	}
	
	@Test 
	public void  testCitiesNotFound() {
		List<City> testCities = new ArrayList<City>();
		
		given(cityRepository.findAllByOrderByNameAsc()).willReturn(testCities);
		
		assertEquals("City lists are empty", testCities, cityService.getCities());
	}
}
