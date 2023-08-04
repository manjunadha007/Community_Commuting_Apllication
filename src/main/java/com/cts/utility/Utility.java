package com.cts.utility;

import com.cts.dto.RideSeekerDTO;

public class Utility {

	public static String generateRsId(RideSeekerDTO rideSeekerDTO) {
		String dob = rideSeekerDTO.getDateOfBirth();
		String yearOfBirth = dob.substring(dob.length() - 2, dob.length());
		return "RE" + rideSeekerDTO.getLastName().substring(0, 2).toUpperCase() + yearOfBirth;
	}
}
