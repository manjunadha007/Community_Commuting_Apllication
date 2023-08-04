package com.cts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class RideSeeker {

	@Id
	private String rsId;

	@NotBlank(message = "Date of birth should be entered")
	private String dateOfBirth;

	@Min(value = 100000000000L, message = "Adhar card must have 12 digits")
	@Max(value = 999999999999L, message = "Adhar card must have 12 digits")
	@Column(unique = true)
	private long adharcard;

	@Pattern(regexp = "[^@\s]+@cognizant.com", message = "emailid should match the constraints")
	@Column(unique = true)
	private String emailId;

	@Min(value = 1000000000, message = "Phone Number should be 10 digits")
	@Max(value = 9999999999L, message = "Phone Number should be 10 digits")
	private long phone;

	@Pattern(regexp = "[a-zA-Z]+", message = "first name should contain alphabets")
	private String firstName;

	@Size(min = 3, message = "last name should contain atleast 3 characters")
	@Pattern(regexp = "[a-zA-Z]+", message = "last name should contain alphabets")
	private String lastName;

	@NotNull(message = "Address is required")
	private String address;

	@Enumerated(EnumType.STRING)
	private Status status;

	public RideSeeker() {

	}

	public RideSeeker(String rsId, String dateOfBirth, long adharcard, String emailId, long phone, String firstName,
			String lastName, String address, Status status) {
		super();
		this.rsId = rsId;
		this.dateOfBirth = dateOfBirth;
		this.adharcard = adharcard;
		this.emailId = emailId;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.status = status;
	}

	public String getRsId() {
		return rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getAdharcard() {
		return adharcard;
	}

	public void setAdharcard(long adharcard) {
		this.adharcard = adharcard;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
