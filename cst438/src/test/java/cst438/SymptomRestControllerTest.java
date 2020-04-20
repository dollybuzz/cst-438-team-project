package cst438;

import org.junit.Before;
import org.junit.Test; //Junit 4
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.controller.SymptomRestController;
import cst438.domain.Symptom;
import cst438.service.SymptomService;

@RunWith(SpringRunner.class)
@WebMvcTest(SymptomRestController.class)
public class SymptomRestControllerTest {
	
	@MockBean
	private SymptomService symptomService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<Symptom> json;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void testGet() throws Exception{
		
	}
	
	

	

}
