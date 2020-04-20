package cst438;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test; //JUnit 4
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
	
	// This object will be magically initialized by the initFields method below.
	private JacksonTester<Symptom> json;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	@Test
	public void contextLoads() {
	}
	
	//Test to ensure that a valid symptom id will elicit a HTTP 200 response
	@Test
	public void testGetIdValid() throws Exception {
		Symptom expected = new Symptom("fever");
		
		given(symptomService.getSymptomId(2)).willReturn(expected);
	
		MockHttpServletResponse response = mvc.perform(get("/api/symptomId/2")
	        .accept(MediaType.APPLICATION_JSON))
	        .andReturn()
	        .getResponse();
	   
	    assertEquals("Response is 200", HttpStatus.OK.value(), response.getStatus());
	}
	
	//Test to ensure that an invalid symptom id will elicit a HTTP 400 response
	@Test
	public void testGetIdInvalid() throws Exception {
		Symptom expected = null;
		
		given(symptomService.getSymptomId(0)).willReturn(expected);
	
		MockHttpServletResponse response = mvc.perform(get("/api/symptomId/0")
	        .accept(MediaType.APPLICATION_JSON))
	        .andReturn()
	        .getResponse();
	   
	    assertEquals("Response is 400", HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	//Test to ensure that a valid symptom string will elicit a HTTP 200 response
	@Test
	public void testGetSymptomValid() throws Exception {
		Symptom expected = new Symptom("fever");
		
		given(symptomService.getSymptomString("fever")).willReturn(expected);
	
		MockHttpServletResponse response = mvc.perform(get("/api/symptomString/fever")
	        .accept(MediaType.APPLICATION_JSON))
	        .andReturn()
	        .getResponse();
	   
	    assertEquals("Response is 200",  HttpStatus.OK.value(), response.getStatus());
	}
	
	//Test to ensure that an invalid symptom string will elicit a HTTP 400 response
	@Test
	public void testGetSymptomInvalid() throws Exception {
		Symptom expected = null;
		
		given(symptomService.getSymptomString("chills")).willReturn(expected);
	
		MockHttpServletResponse response = mvc.perform(get("/api/symptomString/chills")
	        .accept(MediaType.APPLICATION_JSON))
	        .andReturn()
	        .getResponse();
	   
	    assertEquals("Response is 400", HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
}