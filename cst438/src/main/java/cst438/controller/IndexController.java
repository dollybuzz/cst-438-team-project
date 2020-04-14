package cst438.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cst438.domain.*;
import cst438.service.CityService;
import cst438.service.CountryService;
import cst438.service.SymptomService;

@Controller
public class IndexController {

	@Autowired
	private SymptomService symptomService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	private boolean successfulCall;

	@GetMapping("/")
	public String index(Model model) {
		List<Symptom> symptoms = symptomService.getSymptoms();
		if (symptoms.size() == 0) {
			successfulCall = false;
		} else {
			successfulCall = true;
			model.addAttribute("symptoms", symptoms);
		}
		
		List<Country> countries = countryService.getCountries();
		if (countries.size() == 0) {
			successfulCall = false;
		} else {
			successfulCall = true;
			model.addAttribute("countries", countries);
		}
		
		List<City> cities = cityService.getCities();
		if (cities.size() == 0) {
			successfulCall = false;
		} else {
			successfulCall = true;
			model.addAttribute("cities", cities);
		}

		model.addAttribute("success", successfulCall);
		return "index";
	}
}
