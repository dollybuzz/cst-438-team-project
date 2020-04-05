package cst438.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cst438.domain.Country;
import cst438.domain.CountryRepository;

public class UserService {

	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getCountries() {
		return countryRepository.findAllByOrderByNameAsc();
	}
}
