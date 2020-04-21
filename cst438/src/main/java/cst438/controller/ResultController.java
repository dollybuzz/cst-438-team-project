package cst438.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cst438.domain.*;
import cst438.service.CityService;
import cst438.service.CountryService;
import cst438.service.SymptomService;
import cst438.service.UserService;

@Controller
public class ResultController {

	@Autowired
	private SymptomService symptomService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private FanoutExchange fanout;
	
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
			@RequestParam("age") long age,
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
		String symptoms_message = "";
		for(int i = 0; i < symptomVal.length; i++) {
			if (symptomVal[i] == true) {
				x++;
				symptoms_all[i] = "danger";
				symptoms_message += " \"Symtom " + x + "\" : \" " + symptoms.get(i).getSymptom() + " \"";
			}else {
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
		}else if (calculation > 80) {
			message = "You present a large amount of symptoms. Although this is just a preliminary check to see if you should seek medical attention, please make sure you call ahead to let the hospital know you are coming and with the symptoms you are presenting.";
			color = "danger";
			risk = "HIGH RISK";
		}else if (calculation > 60) {
			message = "You present symptoms that we would consider in the cusp of possibly having COVID-19. We would recommend to get tested if possible. If not, continue to monitor your symptoms and seek medical attention if you are not feeling well.";
			color = "warning";
			risk = "MEDIUM-High RISK";
		}else if (calculation > 30) {
			message = "You present a low risk. Possibly have a cold or something else. However, if you start presenting more symptoms, we recommend to seek medical advise. ";
			color = "warning";
			risk = "MEDIUM RISK";
		}else if (calculation > 0) {
			message = "You do not present many symptoms to be worried as having COVID-19, however, you could still be asymptomatic, and should continue to follow the CDC guidelines of social distancing.";
			color = "success";
			risk = "LOW RISK";
		}else {
			message = "You do not show any symptoms that would suggest you have COVID-19, however, just because you do not show symptoms, does not mean you do not have the virus. You can be asymptomatic. Please continue to follow CDC guidelines of social distancing.";
			color = "success";
			risk = "LOW RISK";
		}
		
		String msg = "{\"email\": \"cdc@doesnotexists.org\" \"msg\": \" " + message + "\" " + symptoms_message + " }";
		System.out.println("Message being sent to CDC: " + msg);
		
		rabbitTemplate.convertSendAndReceive(fanout.getName(), "", msg);
		model.addAttribute("symptoms_all", symptoms_all);
		model.addAttribute("success", true);
		model.addAttribute("cityName", cityName);
		model.addAttribute("countryName", countryName);
		model.addAttribute("yes", x);
		model.addAttribute("no", y);
		model.addAttribute("calculation", calculation);
		model.addAttribute("color", color);
		model.addAttribute("risk", risk);
		model.addAttribute("message", message);
		
		//Add use and symptom to database
		UserSymptomList symptomList = userService.configureSymptoms(symptomVal[0], symptomVal[1], symptomVal[2], symptomVal[3], symptomVal[4], symptomVal[5], symptomVal[6], symptomVal[7], symptomVal[8], symptomVal[9], symptomVal[10], symptomVal[11]);
		userService.createUser(countryName, cityName, symptomList, age);
		
		return "results";
	}
	
	
}
