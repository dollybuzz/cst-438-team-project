package cst438;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.controller.UserRestController;
import cst438.domain.User;
import cst438.domain.UserSymptomList;
import cst438.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<User> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void contextLoads() {

	}

	private JacksonTester<User> jsonAttempt;
	private JacksonTester<ArrayList<User>> jsonAttemptList;

	
	
	
	

	/**
	 * Tests for the validity of a search by district
	 * @throws Exception
	 */
	@Test
	public void getUsersByDistrictTest() throws Exception {

		User expectedUser = new User("USA", "California",
				new UserSymptomList(true, true, true, false, false, false, true, true, true, false), 20);
		ArrayList<User> expectedList = new ArrayList<User>();
		expectedList.add(expectedUser);
		
		
		given(userService.getUsersByDistrict("USA")).willReturn(expectedList);

		MockHttpServletResponse response = mvc.perform(
				get("/api/usersByDistrict/USA")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAttemptList
						.write(expectedList)
						.getJson()))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonAttemptList.write(expectedList).getJson());
	}
	
	
	
	
	

	/**
	 * Tests for the validity of a search by symptom
	 * note; this test will need to be revisited as symptom names are finalized
	 * @throws Exception
	 */
	@Test
	public void getUsersBySymptomTest() throws Exception {
		
		String symptomName = "someSymptom";

		User expectedUser = new User("USA", "California",
				new UserSymptomList(true, true, true, false, false, false, true, true, true, false), 20);
		ArrayList<User> expectedList = new ArrayList<User>();
		expectedList.add(expectedUser);
		
		
		given(userService.getUsersByHasSymptom(symptomName)).willReturn(expectedList);

		MockHttpServletResponse response = mvc.perform(
				get("/api/usersBySymptom/someSymptom")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAttemptList
						.write(expectedList)
						.getJson()))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonAttemptList.write(expectedList).getJson());
	}
	
	
	
	
	
	
	/**
	 * Test to ensure that a valid request will elicit a valid HTTP response.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUserGoodTest() throws Exception {

		User expected = new User("USA", "California",
				new UserSymptomList(true, true, true, false, false, false, true, true, true, false), 20);

		given(userService.getUserByID(1)).willReturn(expected);

		MockHttpServletRequestBuilder test = get("/api/userById/1");
		MockHttpServletResponse response = mvc.perform(
				get("/api/userById/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAttempt
						.write(expected)
						.getJson()))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonAttempt.write(expected).getJson());
	}
	
	
	
	
	

	/**
     * Test to ensure that an invalid request will not elicit a valid
     * HTTP response.
     * @throws Exception
     */
	@Test
	public void getUserBadTest() throws Exception {

		int badID = 25;
		User expected = new User(null, null, null, -1);
		
		given(userService.getUserByID(badID)).willReturn(expected);
    	
		MockHttpServletResponse response = mvc.perform(
                get("/api/userById/" + badID).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAttempt.write(expected).getJson()))
                .andReturn().getResponse();
		assertThat(response.getContentAsString()).isEqualTo(jsonAttempt.write(expected).getJson());
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    	
	}
}