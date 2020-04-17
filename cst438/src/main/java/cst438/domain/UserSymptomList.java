package cst438.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserSymptomList")
public class UserSymptomList {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="firstSympt")
	private boolean firstSymptomIsActive;
	@Column(name="secondSympt")
	private boolean secondSymptomIsActive;
	@Column(name="thirdSympt")
	private boolean thirdSymptomIsActive;
	@Column(name="fourthSympt")
	private boolean fourthSymptomIsActive;
	@Column(name="fifthSympt")
	private boolean fifthSymptomIsActive;
	@Column(name="sixthSympt")
	private boolean sixthSymptomIsActive;
	@Column(name="seventhSympt")
	private boolean seventhSymptomIsActive;
	@Column(name="eighthSympt")
	private boolean eighthSymptomIsActive;
	@Column(name="ninthSympt")
	private boolean ninthSymptomIsActive;
	@Column(name="tenthSympt")
	private boolean tenthSymptomIsActive;
	@Column(name="eleventhSympt")
	private boolean eleventhSymptomIsActive;
	@Column(name="twelfthSympt")
	private boolean twelfthSymptomIsActive;
	
	public UserSymptomList(boolean first, boolean second, boolean third, boolean fourth, boolean fifth, boolean sixth, boolean seventh, boolean eighth, boolean ninth, boolean tenth, boolean eleventh, boolean twelfth) {
		firstSymptomIsActive = first;
		secondSymptomIsActive = second;
		thirdSymptomIsActive = third;
		fourthSymptomIsActive = fifth;
		sixthSymptomIsActive = sixth;
		seventhSymptomIsActive = seventh;
		eighthSymptomIsActive = eighth;
		ninthSymptomIsActive = ninth;
		tenthSymptomIsActive = tenth;
		eleventhSymptomIsActive = eleventh;
		twelfthSymptomIsActive = twelfth;
	}
	
	
	public boolean isFirstSymptomIsActive() {
		return firstSymptomIsActive;
	}
	public void setFirstSymptomIsActive(boolean firstSymptomIsActive) {
		this.firstSymptomIsActive = firstSymptomIsActive;
	}
	public boolean isSecondSymptomIsActive() {
		return secondSymptomIsActive;
	}
	public void setSecondSymptomIsActive(boolean secondSymptomIsActive) {
		this.secondSymptomIsActive = secondSymptomIsActive;
	}
	public boolean isThirdSymptomIsActive() {
		return thirdSymptomIsActive;
	}
	public void setThirdSymptomIsActive(boolean thirdSymptomIsActive) {
		this.thirdSymptomIsActive = thirdSymptomIsActive;
	}
	public boolean isFourthSymptomIsActive() {
		return fourthSymptomIsActive;
	}
	public void setFourthSymptomIsActive(boolean fourthSymptomIsActive) {
		this.fourthSymptomIsActive = fourthSymptomIsActive;
	}
	public boolean isFifthSymptomIsActive() {
		return fifthSymptomIsActive;
	}
	public void setFifthSymptomIsActive(boolean fifthSymptomIsActive) {
		this.fifthSymptomIsActive = fifthSymptomIsActive;
	}
	public boolean isSixthSymptomIsActive() {
		return sixthSymptomIsActive;
	}
	public void setSixthSymptomIsActive(boolean sixthSymptomIsActive) {
		this.sixthSymptomIsActive = sixthSymptomIsActive;
	}
	public boolean isSeventhSymptomIsActive() {
		return seventhSymptomIsActive;
	}
	public void setSeventhSymptomIsActive(boolean seventhSymptomIsActive) {
		this.seventhSymptomIsActive = seventhSymptomIsActive;
	}
	public boolean isEighthSymptomIsActive() {
		return eighthSymptomIsActive;
	}
	public void setEighthSymptomIsActive(boolean eighthSymptomIsActive) {
		this.eighthSymptomIsActive = eighthSymptomIsActive;
	}
	public boolean isNinthSymptomIsActive() {
		return ninthSymptomIsActive;
	}
	public void setNinthSymptomIsActive(boolean ninthSymptomIsActive) {
		this.ninthSymptomIsActive = ninthSymptomIsActive;
	}
	public boolean isTenthSymptomIsActive() {
		return tenthSymptomIsActive;
	}
	public void setTenthSymptomIsActive(boolean tenthSymptomIsActive) {
		this.tenthSymptomIsActive = tenthSymptomIsActive;
	}
	public boolean iseleventhSymptomIsActive() {
		return eleventhSymptomIsActive;
	}
	public void setEleventhSymptomIsActive(boolean eleventhSymptomIsActive) {
		this.eleventhSymptomIsActive = eleventhSymptomIsActive;
	}
	public boolean isTwelfthSymptomIsActive() {
		return twelfthSymptomIsActive;
	}
	public void setTwelfthSymptomIsActive(boolean twelfthSymptomIsActive) {
		this.twelfthSymptomIsActive = twelfthSymptomIsActive;
	}

	public long getId() {
		return this.id;
	}

	
	
	@Override
	public String toString() {
		return "UserSymtpomList["
				+ "id= " + id
				+ "firstSymptomIsActive= " + firstSymptomIsActive
				+ "secondSymptomIsActive= " + secondSymptomIsActive
				+ "thirdSymptomIsActive= " + thirdSymptomIsActive
				+ "fourthSymptomIsActive= " + fourthSymptomIsActive
				+ "fifthSymptomIsActive= " + fifthSymptomIsActive
				+ "sixthSymptomIsActive= " + sixthSymptomIsActive
				+ "seventhSymptomIsActive= " + seventhSymptomIsActive
				+ "eighthSymptomIsActive= " + eighthSymptomIsActive
				+ "ninthSymptomIsActive= " + ninthSymptomIsActive
				+ "tenthSymptomIsActive= " + tenthSymptomIsActive
				+ "]";
	}

}
