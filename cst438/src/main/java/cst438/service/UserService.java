package cst438.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cst438.domain.User;
import cst438.domain.UserRepository;


public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		return userRepository.findAllByOrderByIdAsc();
	}
}
