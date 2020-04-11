package cst438;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cst438.controller.CountryRestController;
import cst438.domain.*;
import cst438.service.CountryService;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryRestController.class)
public class CountryRestControllerTest {

	@MockBean
	private CountryService countryService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<Country> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetCountryValid() throws Exception {
		// a list with values should return 200
		Country testCountry = new Country("USA", "United States");
		List<Country> testCountries = new ArrayList<Country>(Arrays.asList(testCountry));
		
		given(countryService.getCountries()).willReturn(testCountries);
	
		MockHttpServletResponse response = mvc.perform(get("/api/countries/")
	        .accept(MediaType.APPLICATION_JSON))
	        .andReturn()
	        .getResponse();
	    
	    assertEquals("Response is 200", response.getStatus(), HttpStatus.OK.value());
	}
	
	@Test
	public void testGetCountryInvalid() throws Exception {
		// an empty list should return 400
		List<Country> testCountries = new ArrayList<Country>();
		
		given(countryService.getCountries()).willReturn(testCountries);
	
		MockHttpServletResponse response = mvc.perform(get("/api/countries/")
	        .accept(MediaType.APPLICATION_JSON))
	        .andReturn()
	        .getResponse();
	    
	    assertEquals("Response is 400", response.getStatus(), HttpStatus.BAD_REQUEST.value());
	}
}
