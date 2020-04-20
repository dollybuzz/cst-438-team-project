package cst438.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {	
	List<User> findAllByOrderByIdAsc();
	
	@Query("select u from User u where district = ?1")
	List<User> findByDistrictOrderByIdAsc(String district);

	@Query(value = "select * from User u natural join user_symptom_list s where ?1 = true", nativeQuery = true)
	List<User> findBySymptomPresent(String symptomName);
}
