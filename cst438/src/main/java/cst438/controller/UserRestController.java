package cst438.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.*;
import cst438.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * HTTP Get route for url.  Retrieves user by database ID.
	 * 
	 * @return HTTP response
	 */
	@GetMapping("/api/userById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = userService.getUserByID(id);
		HttpStatus status;
		
		if (user.getCountryCode() == null) {
			status = HttpStatus.BAD_REQUEST;
			user = new User(null, null, null, -1);
		}
		else {
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<User> (user, status);
	}

	/**
	 * HTTP Get route for url.  Retrieves users by symptom presence.
	 * 
	 * @return HTTP response
	 */
	@GetMapping("/api/usersBySymptom/{symptomName}")
	public ResponseEntity<ArrayList<User>> getUsersBySymptom(@PathVariable("symptomName") String symptom) {
		ArrayList<User> users = userService.getUsersByHasSymptom(symptom);
		HttpStatus status;
		
		status = users.size() == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
		
		return new ResponseEntity<ArrayList<User>> (users, status);
	}

	/**
	 * HTTP Get route for url.  Retrieves user by district.
	 * 
	 * @return HTTP response
	 */
	@GetMapping("/api/usersByDistrict/{district}")
	public ResponseEntity<ArrayList<User>> getUsersByDistrict(@PathVariable("district") String district) {
		ArrayList<User> users = userService.getUsersByDistrict(district);
		HttpStatus status;
		
		status = users.size() == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
		
		return new ResponseEntity<ArrayList<User>> (users, status);
	}
	

	/*
	 * TODO: Still needs validation for district and country code
	 */
	@PostMapping("/api/newUser")
	public ResponseEntity<User> createSymptomList(
			@RequestParam String countryCode,
			@RequestParam String district,
			@RequestParam Long age,
			@RequestParam boolean symptOne,
			@RequestParam boolean symptTwo,
			@RequestParam boolean symptThree,
			@RequestParam boolean symptFour,
			@RequestParam boolean symptFive,
			@RequestParam boolean symptSix,
			@RequestParam boolean symptSeven,
			@RequestParam boolean symptEight,
			@RequestParam boolean symptNine,
			@RequestParam boolean symptTen,
			@RequestParam boolean symptEleven,
			@RequestParam boolean symptTwelve) {
		
		UserSymptomList symptoms = userService.configureSymptoms(
				symptOne, symptTwo, symptThree, symptFour, 
				symptFive, symptSix, symptSeven, symptEight, 
				symptNine, symptTen, symptEleven, symptTwelve);
		User user = userService.createUser(countryCode, district, symptoms, age);
		
		HttpStatus status;
		
		if (age <0)// or any of the data is invalid
		{
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<User> (user, status);
	}
	
}

