package cst438.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cst438.domain.*;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getCities() {
		return cityRepository.findAllByOrderByNameAsc();
	}
}
