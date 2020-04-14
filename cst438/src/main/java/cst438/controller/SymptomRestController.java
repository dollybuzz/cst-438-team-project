package cst438.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import cst438.domain.Symptom;
import cst438.service.SymptomService;

@RestController
public class SymptomRestController {

	@Autowired
	private SymptomService symptomService;
	
	//get complete list of all symptoms
	@GetMapping("/api/symptom")
	public ResponseEntity<List<Symptom>> getAllSymptoms() {
		List<Symptom> symptoms = symptomService.getSymptoms();
		
		if(symptoms.size() == 0) {
			return new ResponseEntity<List<Symptom>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Symptom>>(symptoms, HttpStatus.OK);
	}
	
	//get symptom based on provided id
	@GetMapping("/api/symptom/{id}")
	public ResponseEntity<Symptom> getSymptomById(@PathVariable("id") String id) {
		Long num = Long.valueOf(id);
		Symptom symp = symptomService.getSymptomId(num);
		
		if(symp != null)
		{
			return new ResponseEntity<Symptom>(symp, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Symptom>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//no post mapping since there will be no feature to add new symptoms
	//therefore, symptom table values was manually inputed in MySQL
}
