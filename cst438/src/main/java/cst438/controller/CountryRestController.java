package cst438.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cst438.domain.*;
import cst438.service.CountryService;

@RestController
public class CountryRestController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/api/countries")
	public ResponseEntity<?> getCities() {
		List<Country> countries = countryService.getCountries();

		if (countries.size() == 0) {
			return new ResponseEntity<String>("Unable to fetch countries", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	} 
}
