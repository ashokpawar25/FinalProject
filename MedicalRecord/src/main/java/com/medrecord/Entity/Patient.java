package com.medrecord.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient 
{
	@Id
	private String username;
	private String patientName;
	private String phoneNo;
	private String email;
	private String password;
	private String gender;
	private String bloodGroup;
	private String role;
	private int age;
	private String address;
	private boolean isApproved;
	private Date createdDate;
	private Date lastUpdatedDate;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
	private HealthRecord healthRecord ;

	public Patient() {
		
	}

	public Patient(String username, String patientName, String phoneNo, String email, String password, String gender, String bloodGroup, String role, int age, String address, boolean isApproved, Date createdDate, Date lastUpdatedDate) {
		this.username = username;
		this.patientName = patientName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.role = role;
		this.age = age;
		this.address = address;
		this.isApproved = isApproved;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean approved) {
		isApproved = approved;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
