package com.cts.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cts.dto.BookRideDTO;
import com.cts.dto.RideSeekerDTO;
import com.cts.dto.TripDTO;
import com.cts.entities.RideSeeker;
import com.cts.exceptions.TripNotFoundException;
import com.cts.mapper.RideSeekerMapper;
import com.cts.repos.RideSeekerRepository;
import com.cts.service.impl.RideSeekerServiceImpl;

@SpringBootTest
public class RideSeekerServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private RideSeekerRepository repository;

	@InjectMocks
	private RideSeekerServiceImpl rideSeekerService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateRideSeeker() {
		// Create a RideSeekerDTO object for testing
		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO();
		// Set the necessary properties of rideSeekerDTO for testing

		RideSeeker rideSeeker = RideSeekerMapper.mapToRideSeeker(rideSeekerDTO);
		RideSeeker savedRideSeeker = new RideSeeker();
		// Set the necessary properties of savedRideSeeker

		when(repository.save(any(RideSeeker.class))).thenReturn(savedRideSeeker);

		RideSeekerDTO result = rideSeekerService.createRideSeeker(rideSeekerDTO);

		// Assert the result based on your requirements
	}

	@Test
	public void testUpdateRideSeeker() {
		// Create an existing RideSeeker object
		RideSeeker existingRideSeeker = new RideSeeker();
		// Set the necessary properties of existingRideSeeker

		RideSeeker updatedRideSeeker = new RideSeeker();
		// Set the necessary properties of updatedRideSeeker

		when(repository.findById(anyString())).thenReturn(Optional.of(existingRideSeeker));
		when(repository.save(any(RideSeeker.class))).thenReturn(updatedRideSeeker);

		RideSeeker result = rideSeekerService.updateRideSeeker("rsId", updatedRideSeeker);

		// Assert the result based on your requirements
	}

	@Test
	public void testDeleteRideSeeker() {
		doNothing().when(repository).deleteById(anyString());

		rideSeekerService.deleteRideSeeker("rsId");

		// Verify the repository method is called with the correct arguments
	}

	@Test
	public void testGetTripsBySearchCriteria() {
		// Create a BookRideDTO object for testing
		BookRideDTO bookRideDTO = new BookRideDTO();
		bookRideDTO.setFromLoc("Chennai");
		bookRideDTO.setToLoc("Bangalore");
		bookRideDTO.setRideDate("2023-06-14");
		bookRideDTO.setNoOfRequiredSeats(2);
		// Set the necessary properties of bookRideDTO for testing

		List<TripDTO> trips = new ArrayList<>();
		// Add some TripDTO objects to trips

		ResponseEntity<List> responseEntity = new ResponseEntity<>(trips, HttpStatus.OK);

		when(restTemplate.postForEntity(anyString(), eq(bookRideDTO), eq(List.class))).thenReturn(responseEntity);

	}

	@Test
	public void testGetTripsBySearchCriteria_NoTripsAvailable() {
		// Create a BookRideDTO object for testing
		BookRideDTO bookRideDTO = new BookRideDTO();
		// Set the necessary properties of bookRideDTO for testing

		ResponseEntity<List> responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

		when(restTemplate.postForEntity(anyString(), eq(bookRideDTO), eq(List.class))).thenReturn(responseEntity);

		// Assert that the TripNotFoundException is thrown when no trips are available
		assertThrows(TripNotFoundException.class, () -> {
			rideSeekerService.getTripsBySearchCriteria(bookRideDTO);
		});
	}

	@Test
	public void testGetTripsBySearchCriteria_InvalidRequiredSeats() {
		// Create a BookRideDTO object with invalid required seats
		BookRideDTO bookRideDTO = new BookRideDTO();
		bookRideDTO.setFromLoc("VHgvhgv");
		bookRideDTO.setToLoc("kjrhkr");
		bookRideDTO.setRideDate("2374-75-09");
		bookRideDTO.setNoOfRequiredSeats(0);
		// Set the necessary properties of bookRideDTO for testing

		// Assert that the TripNotFoundException is thrown when required seats are less
		// than or equal to zero
		assertThrows(TripNotFoundException.class, () -> {
			rideSeekerService.getTripsBySearchCriteria(bookRideDTO);
		});
	}

	@Test
	public void testGetRideSeekerById() {
		// Create a RideSeeker object for testing
		RideSeeker rideSeeker = new RideSeeker();
		// Set the necessary properties of rideSeeker for testing

		when(repository.findById(anyString())).thenReturn(Optional.of(rideSeeker));

		RideSeekerDTO result = rideSeekerService.getRideSeekerById("id");

		// Assert the result based on your requirements
	}

	@Test
	void getTripsBySearchCriteria_NoTripsAvailable() {
		// Create a mock BookRideDTO object
		BookRideDTO bookRideDTO = new BookRideDTO();
		bookRideDTO.setRideDate("2023-06-15");
		bookRideDTO.setNoOfRequiredSeats(2);

		// Create a mock ResponseEntity containing an empty list of trips
		List<TripDTO> trips = new ArrayList<>();
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(trips, HttpStatus.OK);

		// Mock the postForEntity method of the RestTemplate to return the mock
		// ResponseEntity
		RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
		Mockito.when(restTemplateMock.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
				.thenReturn(responseEntity);

		// Create an instance of the class under test
		RideSeekerServiceImpl rideSeekerService = new RideSeekerServiceImpl();

		// Invoke the method and assert that it throws TripNotFoundException
		assertThrows(TripNotFoundException.class, () -> rideSeekerService.getTripsBySearchCriteria(bookRideDTO));
	}

}
