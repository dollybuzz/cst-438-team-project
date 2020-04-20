package cst438;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test; //JUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438.domain.Symptom;
import cst438.domain.SymptomRepository;
import cst438.service.SymptomService;

@SpringBootTest
public class SymptomServiceTest {
	@Autowired
	private SymptomService symptomService;
	@MockBean 
	private SymptomRepository symptomRepository;
	
	@Test
	public void contextLoads()
	{}
	
	//Test that the entire symptom list will return
	@Test
	public void testGetAllSymptoms() {
		List<Symptom> allSymptoms = new ArrayList<Symptom>();
		
		given(symptomRepository.findAllByOrderById()).willReturn(allSymptoms);
		
		List<Symptom> actualSymptoms = symptomService.getSymptoms();
		
		assertThat(actualSymptoms).isEqualTo(allSymptoms);
	}
	
	//Test that a valid id search returns correct symptom
	@Test
	public void  testValidSymptomId() {
		Symptom testSymptom = new Symptom("fever");
		
		given(symptomRepository.findById(2)).willReturn(testSymptom);
		
		Symptom actualSymp = symptomService.getSymptomId(2);
		
		assertThat(actualSymp).isEqualTo(testSymptom);
	}
	
	//Test that an invalid id returns null
	@Test
	public void  testInvalidSymptomId() {
		Symptom testSymptom = null;
		
		given(symptomRepository.findById(0)).willReturn(testSymptom);
		
		Symptom actualSymp = symptomService.getSymptomId(0);
		
		assertThat(actualSymp).isEqualTo(testSymptom);
	}
	
	//Test that a valid symptom string returns the right id
	@Test
	public void  testValidSymptomString() {
		Symptom testSymptom = new Symptom("chills");
		
		given(symptomRepository.findBySymptom("chills")).willReturn(testSymptom);
		
		Symptom actualSymp = symptomService.getSymptomString("chills");
		
		assertThat(actualSymp).isEqualTo(testSymptom);
	}
	
	//Test that an invalid symptom returns null
	@Test
	public void  testInvalidSymptomString() {
		Symptom testSymptom = null;
		
		given(symptomRepository.findBySymptom("chills")).willReturn(testSymptom);
		
		Symptom actualSymp = symptomService.getSymptomString("chills");
		
		assertThat(actualSymp).isEqualTo(testSymptom);
	}
}