package com.cts.service;

import java.util.List;

import com.cts.dto.BookRideDTO;
import com.cts.dto.RideSeekerDTO;
import com.cts.dto.TripDTO;
import com.cts.entities.RideSeeker;

public interface RideSeekerService {
	RideSeekerDTO createRideSeeker(RideSeekerDTO rideSeekerDTO);

	RideSeeker updateRideSeeker(String rsId, RideSeeker rideSeeker);

	void deleteRideSeeker(String rs_id);

	List<TripDTO> getTripsBySearchCriteria(BookRideDTO bookRideDTO);

	RideSeekerDTO getRideSeekerById(String id);
}
