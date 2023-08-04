package com.cts.dto;

import java.sql.Date;
import java.sql.Time;

public class TripDTO {

	private String tripId;
	private String creatorUserId;
	private String vehicleId;
	private Date rideDate;
	private Time rideTime;
	private RideStatus rideStatus;
	private int noOfSeat;
	private int seatsFilled;
	private String fromLoc;
	private String toLoc;

	public TripDTO() {
	}

	public TripDTO(String tripId, String creatorUserId, String vehicleId, Date rideDate, Time rideTime,
			RideStatus rideStatus, int noOfSeat, int seatsFilled, String fromLoc, String toLoc) {
		super();
		this.tripId = tripId;
		this.creatorUserId = creatorUserId;
		this.vehicleId = vehicleId;
		this.rideDate = rideDate;
		this.rideTime = rideTime;
		this.rideStatus = rideStatus;
		this.noOfSeat = noOfSeat;
		this.seatsFilled = seatsFilled;
		this.fromLoc = fromLoc;
		this.toLoc = toLoc;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

	public Time getRideTime() {
		return rideTime;
	}

	public void setRideTime(Time rideTime) {
		this.rideTime = rideTime;
	}

	public RideStatus getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public int getSeatsFilled() {
		return seatsFilled;
	}

	public void setSeatsFilled(int seatsFilled) {
		this.seatsFilled = seatsFilled;
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

}
