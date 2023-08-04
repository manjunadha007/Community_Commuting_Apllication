package com.cts.exceptions;

public class TripNotFoundException extends RuntimeException {
	
	public TripNotFoundException(String msg) {
		super(msg);
	}
}
