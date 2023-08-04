package com.cts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.dto.BookRideDTO;
import com.cts.dto.RideSeekerDTO;
import com.cts.dto.TripDTO;
import com.cts.entities.RideSeeker;
import com.cts.exceptions.RideSeekerNotFoundException;
import com.cts.exceptions.TripNotFoundException;
import com.cts.mapper.RideSeekerMapper;
import com.cts.repos.RideSeekerRepository;
import com.cts.service.RideSeekerService;

import jakarta.validation.Valid;

@Service
public class RideSeekerServiceImpl implements RideSeekerService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RideSeekerRepository repository;

	public RideSeekerServiceImpl(RideSeekerRepository repository) {
		this.repository = repository;
	}

	public RideSeekerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public RideSeekerDTO createRideSeeker(RideSeekerDTO rideSeekerDTO) {

		RideSeeker rideSeeker = RideSeekerMapper.mapToRideSeeker(rideSeekerDTO);

		RideSeeker savedRideSeeker = repository.save(rideSeeker);

		RideSeekerDTO savedRideSeekerDTO = RideSeekerMapper.mapToRideSeekerDTO(savedRideSeeker);

		return savedRideSeekerDTO;
	}

	@Override
	public RideSeeker updateRideSeeker(String rsId, @Valid RideSeeker rideSeeker) {
		RideSeeker existingrideSeeker = repository.findById(rsId)
				.orElseThrow(() -> new RideSeekerNotFoundException("The user " + rsId + " not found in the database"));

		RideSeeker updatedRideSeeker = updateRideSeeker(rideSeeker, existingrideSeeker);
		return repository.save(updatedRideSeeker);

	}

	private RideSeeker updateRideSeeker(RideSeeker rideSeeker, RideSeeker existingrideSeeker) {
		if (rideSeeker.getDateOfBirth() != null) {
			existingrideSeeker.setDateOfBirth(rideSeeker.getDateOfBirth());
		}

		if (rideSeeker.getAdharcard() != 0) {
			existingrideSeeker.setAdharcard(rideSeeker.getAdharcard());
		}

		if (rideSeeker.getEmailId() != null) {
			existingrideSeeker.setEmailId(rideSeeker.getEmailId());
		}

		if (rideSeeker.getPhone() != 0) {
			existingrideSeeker.setPhone(rideSeeker.getPhone());
		}

		if (rideSeeker.getFirstName() != null) {
			existingrideSeeker.setFirstName(rideSeeker.getFirstName());

		}

		if (rideSeeker.getLastName() != null) {
			existingrideSeeker.setLastName(rideSeeker.getLastName());
		}

		if (rideSeeker.getAddress() != null) {
			existingrideSeeker.setAddress(rideSeeker.getAddress());
		}

		if (rideSeeker.getStatus() != null) {
			existingrideSeeker.setStatus(rideSeeker.getStatus());
		}

		return existingrideSeeker;

	}

	@Override
	public void deleteRideSeeker(String rs_id) {

		repository.deleteById(rs_id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TripDTO> getTripsBySearchCriteria(BookRideDTO bookRideDTO) {
		System.out.println(bookRideDTO.getRideDate());

		ResponseEntity<List> response = restTemplate.postForEntity("http://localhost:8080/api/tripmanager/bookRide",
				bookRideDTO, List.class);

		List<TripDTO> listOfTrips = response.getBody();
		System.out.println(listOfTrips);
		// This is to raise an exception when required number of seats are less than or
		// equal to zero
		if (bookRideDTO.getNoOfRequiredSeats() <= 0) {
			throw new TripNotFoundException("No of Booked seats should be greater than zero");
		}

		// It is to raise an exception if there are no trips available which matches
		// search criteria
		if (listOfTrips.size() == 0) {
			throw new TripNotFoundException("No trip is available");
		}
		return listOfTrips;

//		List<TripDTO> matchedTrips = new ArrayList<>();
//
//		// It is to raise an exception if the Required Seats are greater than available
//		// seats
//		for (TripDTO trip : listOfTrips) {
//			int availableSeats = trip.getNoOfSeat() - trip.getSeatsFilled();
//			System.out.println(availableSeats);
//			if (bookRideDTO.getNoOfRequiredSeats() > availableSeats) {
//				continue;
//			} else {
//				matchedTrips.add(trip);
//			}
//		}
//
//		if (matchedTrips.size() == 0) {
//			System.out.println(matchedTrips.size());
//			throw new TripNotFoundException("Required seats should be less than or equal to available seats");
//
//		} else {
//			return listOfTrips;
//		}

//		return listOfTrips;

	}

	@Override
	public RideSeekerDTO getRideSeekerById(String id) {
		RideSeeker rideSeeker = repository.findById(id).get();
		RideSeekerDTO rideSeekerDTO = RideSeekerMapper.mapToRideSeekerDTO(rideSeeker);
		return rideSeekerDTO;
	}

}
