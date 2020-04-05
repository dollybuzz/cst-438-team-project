package cst438.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(name="countryCode")
	private String countryCode;
	@Column(name="district")
	private String district;
	@Column(name="symptomListId")
	private int symptomListId;
	
	
	
	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public int getSymptomListId() {
		return symptomListId;
	}



	public void setSymptomListId(int symptomListId) {
		this.symptomListId = symptomListId;
	}



	@Override
	public String toString() {
		return "{"
				+ "id: " + id
				+ "coutryCode: " + countryCode
				+ "district: " + district
				+ "symptomListId: " + symptomListId
				+ "}";
	}
	
	
	
}
