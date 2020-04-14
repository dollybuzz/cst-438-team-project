package cst438.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
	
	List<Symptom> findAllByOrderById();
	
	Symptom findById(long id);
}
