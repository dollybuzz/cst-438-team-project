package cst438.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symptom")
public class Symptom {
	

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="symptom")
	private String symptom;
	
	public Symptom() {
		this("symptom");
	}
	
	public Symptom(String symptom)
	{
		super();
		this.symptom = symptom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "id: " + id
				+ "symptom: " + symptom
				+ "}";
	}

}
