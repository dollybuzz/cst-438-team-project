package cst438.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438.domain.Symptom;
import cst438.domain.SymptomRepository;

@Service
public class SymptomService {
	
		private static final Logger log = LoggerFactory.getLogger(SymptomService.class);
	
		@Autowired
		private SymptomRepository symptomRepository;
		
		public List<Symptom> getSymptoms() {
			if (symptomRepository.findAllByOrderById() != null)
			{
				return symptomRepository.findAllByOrderById();
			}
			else
			{
				log.info("Symptom list not found.");
				return null;
			}
		}
		
		public Symptom getSymptomId(long id) {
			
			Symptom symp = symptomRepository.findById(id);
			
			if(symp != null)
			{
				return symp;
			}
			else
			{
				log.info("Symptom id was not valid.");
				return null;
			}
		}
		
		public Symptom getSymptomString(String symptom)
		{
			Symptom symp = symptomRepository.findBySymptom(symptom);
			
			if(symp != null)
			{
				return symp;
			}
			else
			{
				log.info("Symptom string was not valid.");
				return null;
			}
		}
}
