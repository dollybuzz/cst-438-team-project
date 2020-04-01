package cst438.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cst438.domain.*;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getCountries() {
		return countryRepository.findAllByOrderByNameAsc();
	}
}
