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
	
	@MockBean SymptomRepository symptomRepository;
	
	
	@Test
	public void contextLoads()
	{}
	
	//Test to ensure the entire symptom result will return
	@Test
	public void testSymptomGetAll() throws Exception{
		List<Symptom> allSymptoms = new ArrayList<Symptom>();
		allSymptoms.add(new Symptom("fever"));
		allSymptoms.add(new Symptom("cough/dry cough"));
		allSymptoms.add(new Symptom("runny nose"));
			
		given(symptomRepository.findAllByOrderById()).willReturn(allSymptoms);
		
		List<Symptom> actualSymptoms = symptomService.getSymptoms();
		assertThat(actualSymptoms).isEqualTo(allSymptoms);
	}
	
	//Test that a valid id search returns correct symptom
	@Test
	public void  testValidSymptomId() {
		Symptom testSymp = new Symptom("cough/dry cough");
		
		given(symptomRepository.findById(1)).willReturn(testSymp);
		
		Symptom actualSymp = symptomService.getSymptomId(1);
		
		assertThat(actualSymp).isEqualTo(testSymp);
	}
	
	//Test that an invalid id returns null
	@Test
	public void  testInvalidSymptomId() {
		Symptom testSymp = null;
		
		given(symptomRepository.findById(0)).willReturn(testSymp);
		
		Symptom actualSymp = symptomService.getSymptomId(0);
		
		assertThat(actualSymp).isEqualTo(testSymp);
	}
	
	//Test that a valid symptom string returns the correct symptom
	@Test
	public void  testValidSymptomString() {
		Symptom testSymp = new Symptom("fever");
		
		given(symptomRepository.findBySymptom("fever")).willReturn(testSymp);
	
		Symptom actualSymp = symptomService.getSymptomString("fever");
		
		assertThat(actualSymp).isEqualTo(testSymp);
	}
	
	
	//Test that an invalid symptom returns null
	@Test
	public void  testInvalidSymptomString() {
		Symptom testSymp = null;
		
		given(symptomRepository.findBySymptom("cancer")).willReturn(testSymp);
	
		Symptom actualSymp = symptomService.getSymptomString("cancer");
		
		assertThat(actualSymp).isEqualTo(testSymp);
	}
	
	
}
