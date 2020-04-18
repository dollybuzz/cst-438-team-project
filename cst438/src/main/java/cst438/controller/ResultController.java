package cst438.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cst438.domain.*;
import cst438.service.CityService;
import cst438.service.CountryService;
import cst438.service.SymptomService;

@Controller
public class ResultController {

	@Autowired
	private SymptomService symptomService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	private boolean successfulCall;

	@GetMapping("/results")
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
	
	@PostMapping("/results")
	public String result(
			@RequestParam("country") String countryName,
			@RequestParam("city") String cityName,
			@RequestParam("symptom-val") Boolean[] symptomVal,
			Model model) {
		
		
		List<Symptom> symptoms = symptomService.getSymptoms();
		if (symptoms.size() == 0) {
			successfulCall = false;
		} else {
			successfulCall = true;
			model.addAttribute("symptoms", symptoms);
		}
		
		int x = 0;
		int y = 0;
		String[] symptoms_all = new String[symptoms.size()];
		int[] symptoms_showing = new int[symptoms.size()];
		int[] symptoms_not_showing = new int[symptoms.size()];
		for(int i = 0; i < symptomVal.length; i++) {
			if (symptomVal[i] == true) {
				symptoms_showing[x] = i;
				x++;
				symptoms_all[i] = "danger";
			}else {
				symptoms_not_showing[y] = i;
				y++;
				symptoms_all[i] = "";
			}
		}
		String message;
		String color;
		String risk;
		double xi = x;
		double yi = y;
		double calculation = (xi / (xi+yi)) * 100;
		if (calculation == 100) {
			message = "Based on your results, you are experiencing all of the symptoms a typical COVID-19 patient would display. We highly recommend to seek medical attention.";
			color = "danger";
			risk = "HIGH RISK";
		}else if (calculation > 90) {
			message = "You present a large amount of symptoms. Although this is just checking to see if you ";
			color = "danger";
			risk = "MEDIUM-HIGH RISK";
		}else if (calculation > 60) {
			message = "";
			color = "warning";
			risk = "MEDIUM RISK";
		}else if (calculation > 30) {
			message = "You present a low risk. Possibly have a cold or something else. ";
			color = "warning";
			risk = "MEDIUM-LOW RISK";
		}else if (calculation > 0) {
			message = "";
			color = "success";
			risk = "LOW RISK";
		}else {
			message = "You do not show any symptoms that would suggest you have COVID-19, however, just because you do not show symptoms, does not mean you do not have the virus. You can be asymptomatic.  ";
			color = "success";
			risk = "LOW RISK";
		}

		model.addAttribute("showing", symptoms_showing);
		model.addAttribute("symptoms_all", symptoms_all);
		model.addAttribute("notShowing", symptoms_not_showing);
		model.addAttribute("success", true);
		model.addAttribute("cityName", cityName);
		model.addAttribute("countryName", countryName);
		model.addAttribute("yes", x);
		model.addAttribute("no", y);
		model.addAttribute("calculation", calculation);
		model.addAttribute("color", color);
		model.addAttribute("risk", risk);
		model.addAttribute("message", message);
		return "results";
	}
}
