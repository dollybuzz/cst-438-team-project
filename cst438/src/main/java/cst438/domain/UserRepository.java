package cst438.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {	
	List<User> findAllByOrderByIdAsc();
	
	@Query("select u from users u where district = ?1")
	List<User> findByDistrictOrderByIdAsc(String district);

	@Query("select u from users u natural join userSymptomList s where ?1 = true")
	List<User> findBySymptomPresent(String symptomName);
}
