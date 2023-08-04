package com.cts.dto;

public class BookRideDTO {

	private String fromLoc;
	private String toLoc;
	private String rideDate;
	private int noOfRequiredSeats;

	public BookRideDTO() {
	}

	public BookRideDTO(String fromLoc, String toLoc, String rideDate, int noOfRequiredSeats) {
		super();
		this.fromLoc = fromLoc;
		this.toLoc = toLoc;
		this.rideDate = rideDate;
		this.noOfRequiredSeats = noOfRequiredSeats;
	}

	public String getFromLoc() {
		return fromLoc;
	}

	public void setFromLoc(String fromLoc) {
		this.fromLoc = fromLoc;
	}

	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(String toLoc) {
		this.toLoc = toLoc;
	}

	public String getRideDate() {
		return rideDate;
	}

	public void setRideDate(String rideDate) {
		this.rideDate = rideDate;
	}

	public int getNoOfRequiredSeats() {
		return noOfRequiredSeats;
	}

	public void setNoOfRequiredSeats(int noOfRequiredSeats) {
		this.noOfRequiredSeats = noOfRequiredSeats;
	}

}
