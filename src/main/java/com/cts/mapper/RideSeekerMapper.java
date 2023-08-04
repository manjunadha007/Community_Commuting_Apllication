 package com.cts.mapper;

import com.cts.dto.RideSeekerDTO;
import com.cts.entities.RideSeeker;

public class RideSeekerMapper {

	// Converts RideSeeker JPA Entity to RideSeekerDTO
	public static RideSeekerDTO mapToRideSeekerDTO(RideSeeker rideSeeker) {
		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO(rideSeeker.getRsId(), rideSeeker.getDateOfBirth(),
				rideSeeker.getAdharcard(), rideSeeker.getEmailId(), rideSeeker.getPhone(), rideSeeker.getFirstName(),
				rideSeeker.getLastName(), rideSeeker.getAddress(), rideSeeker.getStatus());

		return rideSeekerDTO;
	}

	// Converts RideSeeker DTO to RideSeeker JPA Entity
	public static RideSeeker mapToRideSeeker(RideSeekerDTO rideSeekerDTO) {
		RideSeeker rideSeeker = new RideSeeker(rideSeekerDTO.getRsId(), rideSeekerDTO.getDateOfBirth(),
				rideSeekerDTO.getAdharcard(), rideSeekerDTO.getEmailId(), rideSeekerDTO.getPhone(),
				rideSeekerDTO.getFirstName(), rideSeekerDTO.getLastName(), rideSeekerDTO.getAddress(),
				rideSeekerDTO.getStatus());

		return rideSeeker;
	}

}
