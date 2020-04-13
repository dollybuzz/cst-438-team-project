package cst438.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import cst438.domain.User;
import cst438.domain.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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

		return user.isPresent() ? user.get() : new User(null, null, null, -1);
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

	/**
	 * Fetches a list of users within the given district
	 * 
	 * @param district - Sakila World Database district of a given area
	 * @return List of users found
	 */
	public ArrayList<User> getUsersByDistrict(String district) {
		return new ArrayList<User>(userRepository.findByDistrictOrderByIdAsc(district));
	}
}
