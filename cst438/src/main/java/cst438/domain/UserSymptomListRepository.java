package cst438.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSymptomListRepository extends JpaRepository<UserSymptomList, String> {
	List<UserSymptomList> findAllByOrderByIdAsc();
}
