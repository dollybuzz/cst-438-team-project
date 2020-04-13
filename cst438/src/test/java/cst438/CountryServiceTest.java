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
import cst438.service.CountryService;
 
@SpringBootTest
public class CountryServiceTest {
	
	@Autowired
	private CountryService countryService;
	
	@MockBean
	private CountryRepository countryRepository;
	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCountriesFound() throws Exception {
		Country testCountry = new Country("USA", "United States");
		List<Country> testCountries = new ArrayList<Country>(Arrays.asList(testCountry));
		
		given(countryRepository.findAllByOrderByNameAsc()).willReturn(testCountries);

		assertEquals("Country lists are equal", testCountries, countryService.getCountries());
	}
	
	@Test 
	public void  testCountriesNotFound() {
		List<Country> testCountries = new ArrayList<Country>();
		
		given(countryRepository.findAllByOrderByNameAsc()).willReturn(testCountries);
		
		assertEquals("Country lists are empty", testCountries, countryService.getCountries());
	}
}
