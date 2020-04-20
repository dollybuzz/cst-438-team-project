package cst438.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438.domain.User;
import cst438.domain.UserRepository;
import cst438.domain.UserSymptomList;
import cst438.domain.UserSymptomListRepository;

@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(SymptomService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired UserSymptomListRepository symptoms;

	public List<User> getUsers() {
		return userRepository.findAllByOrderByIdAsc();
	}

	/**
	 * Fetches a user by database ID field
	 * 
	 * @param id - long corresponding to the desired user's database ID field
	 * @return - a user Object or null if not found
	 */
	public User getUserByID(long id) {
		Optional<User> user = userRepository.findById(Long.toString(id));

		if (user.isPresent()) {
			return user.get();
		}
		else {
			log.info("User not found.");
			return new User(null, null, null, -1);
		}
	}

	/**
	 * Fetches a list of users with the given symptom
	 * 
	 * @param symptomName - database column name of desired symptom
	 * @return list of users with the given symptom
	 */
	public ArrayList<User> getUsersByHasSymptom(String symptomName) {

		return new ArrayList<User>(userRepository.findBySymptomPresent(symptomName));
	}

	/**,
	 * Fetches a list of users within the given district
	 * 
	 * @param district - Sakila World Database district of a given area
	 * @return List of users found
	 */
	public ArrayList<User> getUsersByDistrict(String district) {
		return new ArrayList<User>(userRepository.findByDistrictOrderByIdAsc(district));
	}
	
	public UserSymptomList configureSymptoms(boolean first, boolean second, boolean third, boolean fourth, boolean fifth, boolean sixth, boolean seventh, boolean eighth, boolean ninth, boolean tenth, boolean eleventh, boolean twelfth) {
		UserSymptomList symptoms = new UserSymptomList(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth);
		return symptoms;		
	}
	
	
	/*
	 * save user to repo
	 */
	public User createUser(String countryCode, String district, UserSymptomList symptomList, long age) {
		if (age < 0 || age > 150) {
			log.info("User age was less than 0 or greater than 150");
			age = -1;
		}
		if (symptomList == null) {
			log.info("Symptom List is null");
		}
		else {
		symptoms.save(symptomList);
		}
		User user = new User(countryCode, district, symptomList, age);
		userRepository.save(user);
		return user;
	}
}
