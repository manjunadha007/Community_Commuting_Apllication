package com.cts.dto;

import com.cts.entities.Status;

public class RideSeekerDTO {

	private String rsId;
	private String dateOfBirth;
	private long adharcard;
	private String emailId;
	private long phone;
	private String firstName;
	private String lastName;
	private String address;
	private Status status;

	public RideSeekerDTO() {

	}

	public RideSeekerDTO(String rsId, String dateOfBirth, long adharcard, String emailId, long phone, String firstName,
			String lastName, String address, Status status) {
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
