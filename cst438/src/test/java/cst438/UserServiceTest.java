package cst438;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import cst438.domain.*;
import cst438.service.UserService;
 
@SpringBootTest
public class UserServiceTest {

	
	@Autowired
	private UserService userService;
	
	@MockBean 
	private UserRepository userRepo;

	private UserSymptomList allTrue = new UserSymptomList(false, true, false, false, false, false, false, false, false, false);
	private UserSymptomList allFalse = new UserSymptomList(false, false, false, false, false, false, false, false, false, true);
	private User testInfoPos1 = new User("USA", "California", allTrue, 20); 
	private User testInfoNeg1 = new User("USA", "New York", allFalse, 25); 
	private User testInfoPos2 = new User("GBR", "London", allTrue, 30); 
	private User testInfoNeg2 = new User("GBR", "Sheffield", allFalse, 35); 
	
	@Test
	public void contextLoads() {
	}
	
	/**
	 * test that a general search will return all
	 * @throws Exception
	 */
	public void testUsersFindAll() throws Exception{
		List<User> allUsers = new ArrayList<User>();
		allUsers.add(testInfoPos1);
		allUsers.add(testInfoNeg1);
		allUsers.add(testInfoPos2);
		allUsers.add(testInfoNeg2);
		
		given(userRepo.findAllByOrderByIdAsc()).willReturn(allUsers);
		
		List<User> actualUsers = userService.getUsers();
		assertThat(actualUsers).isEqualTo(allUsers);
	}
	
	/**
	 * Test that a search for users with a given symptom returns the correct list of users.
	 * @throws Exception
	 */
	@Test
	public void testUsersFoundBySymptom() throws Exception {
		List<User> positiveUsersTest = new ArrayList<User>();
		positiveUsersTest.add(testInfoPos1);
		positiveUsersTest.add(testInfoPos2);
		
		
		given(userRepo.findBySymptomPresent("Symptom2")).willReturn(positiveUsersTest);
		
		
		ArrayList<User> actualUsers = userService.getUsersByHasSymptom("Symptom2");
		assertThat(actualUsers).isEqualTo(positiveUsersTest);
	}

	/**
	 * Test that a search for users in a given district returns the correct list of users.
	 * @throws Exception
	 */
	@Test
	public void testUsersFoundByDistrict() throws Exception {
		List<User> ukUsersTest = new ArrayList<User>();
		ukUsersTest.add(testInfoNeg2);
		ukUsersTest.add(testInfoPos2);
		
		
		given(userRepo.findByDistrictOrderByIdAsc("England")).willReturn(ukUsersTest);
		
		
		ArrayList<User> actualUsers = userService.getUsersByDistrict("England");
		assertThat(actualUsers).isEqualTo(ukUsersTest);
	}

	/**
	 * Test that a valid ID search resolves to the correct user.
	 * @throws Exception
	 */
	@Test
	public void testUserFoundByID() throws Exception {
		Optional<User> optionalVersion = Optional.of(testInfoPos1);
		
		given(userRepo.findById(Long.toString(1))).willReturn(optionalVersion);
		
		
		User actualUser = userService.getUserByID(1);
		assertThat(actualUser).isEqualTo(testInfoPos1);
	}
	
	/**
	 * Test that an invalid id returns a null value.
	 */
	@Test 
	public void  testUserNotFoundByID() {
		User testUser = new User(null, null, allFalse, -1);
		List<User> allUsersTest = new ArrayList<User>();
		allUsersTest.add(testInfoPos1);
		allUsersTest.add(testInfoNeg1);
		allUsersTest.add(testInfoPos2);
		allUsersTest.add(testInfoNeg2);
		
		
		given(userRepo.findAllByOrderByIdAsc()).willReturn(allUsersTest);
		
		
		User actualUser = userService.getUserByID(25);
		assertThat(actualUser).isEqualTo(testUser);
	}
	
}
