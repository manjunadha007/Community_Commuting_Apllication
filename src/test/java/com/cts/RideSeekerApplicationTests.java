package com.cts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cts.controller.RideSeekerRestController;
import com.cts.dto.BookRideDTO;
import com.cts.dto.RideSeekerDTO;
import com.cts.dto.RideStatus;
import com.cts.dto.TripDTO;
import com.cts.entities.RideSeeker;
import com.cts.entities.Status;
import com.cts.exceptions.GlobalExceptionHandler;
import com.cts.exceptions.RideSeekerNotFoundException;
import com.cts.mapper.RideSeekerMapper;
import com.cts.repos.RideSeekerRepository;
import com.cts.service.RideSeekerService;
import com.cts.utility.Utility;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@SpringBootTest
class RideSeekerApplicationTests {

	@Autowired
	RideSeekerRepository rideSeekerRepo;

	@Autowired
	RideSeekerService rideSeekerService;

	@Autowired
	private RideSeekerRestController rideSeekerRestController;

	@Test
	void contextLoads() {
	}

	@Test
	void restTemplateBeanCreation() {
		// Test if the RestTemplate bean is created successfully
		RideSeekerApplication application = new RideSeekerApplication();
		RestTemplate restTemplate = application.restTemplate();

		// Assert that the RestTemplate bean is not null
		assertNotNull(restTemplate);
	}

	@Test
	public void testSaveNewProduct() {
		@Valid
		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO();

		rideSeekerDTO.setDateOfBirth("12/13/2222");
		rideSeekerDTO.setAdharcard(533221234567L);
		rideSeekerDTO.setEmailId("manjureddy@cognizant.com");
		rideSeekerDTO.setPhone(9849669707L);
		rideSeekerDTO.setFirstName("ManjuReddy");
		rideSeekerDTO.setLastName("AKASHddy");
		rideSeekerDTO.setAddress("Kadapa");
		Status status = Status.Registered;
		rideSeekerDTO.setStatus(status);

		String rsId = Utility.generateRsId(rideSeekerDTO);
		rideSeekerDTO.setRsId(rsId);

		RideSeeker rideSeeker = RideSeekerMapper.mapToRideSeeker(rideSeekerDTO);

		RideSeeker save = rideSeekerRepo.save(rideSeeker);
		RideSeekerDTO savedRideSeekerDTO = RideSeekerMapper.mapToRideSeekerDTO(save);

		assertThat(savedRideSeekerDTO.getRsId()).isEqualTo("REAK22");
	}

	@Test
	public void testBookRideDTO() {
		@Valid
		BookRideDTO bookRideDTO = new BookRideDTO();

		bookRideDTO.setFromLoc("Kadapa");
		bookRideDTO.setToLoc("Kurnool");
		bookRideDTO.setRideDate("12/06/2023");
		bookRideDTO.setNoOfRequiredSeats(2);

		assertThat(bookRideDTO.getFromLoc()).isEqualTo("Kadapa");
		assertThat(bookRideDTO.getToLoc()).isEqualTo("Kurnool");
		assertThat(bookRideDTO.getRideDate()).isEqualTo("12/13/2222");
		assertThat(bookRideDTO.getNoOfRequiredSeats()).isEqualTo(2);
	}

	@Test
	public void testBookRideDTO1() {
		@Valid
		BookRideDTO bookRideDTO = new BookRideDTO("Kadapa", "Kurnool", "12/01/2022", 2);

		assertThat(bookRideDTO.getFromLoc()).isEqualTo("Kadapa");
		assertThat(bookRideDTO.getToLoc()).isEqualTo("Kurnool");
		assertThat(bookRideDTO.getRideDate()).isEqualTo("12/06/2023");
	}

	@Test
	public void testTripDTO() {
		TripDTO tripDTO = new TripDTO();
		tripDTO.setTripId("MA12TE");
		tripDTO.setCreatorUserId("Manju123");
		tripDTO.setVehicleId("ABCD123");
		RideStatus status = RideStatus.Completed;
		tripDTO.setRideStatus(status);
		tripDTO.setNoOfSeat(20);
		tripDTO.setSeatsFilled(1);
		tripDTO.setFromLoc("Bangalore");
		tripDTO.setToLoc("Chennai");

		assertThat(tripDTO.getTripId()).isEqualTo("MA12TE");
		assertThat(tripDTO.getCreatorUserId()).isEqualTo("Manju123");
		assertThat(tripDTO.getVehicleId()).isEqualTo("ABCD123");
		assertThat(tripDTO.getRideStatus()).isEqualTo("Completed");
		assertThat(tripDTO.getNoOfSeat()).isEqualTo(20);
		assertThat(tripDTO.getSeatsFilled()).isEqualTo(1);
		assertThat(tripDTO.getFromLoc()).isEqualTo("Bangalore");
		assertThat(tripDTO.getToLoc()).isEqualTo("Chennai");

	}

	@Test
	public void testCreateRideSeeker() {
		@Valid
		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO();

		rideSeekerDTO.setDateOfBirth("12/13/2222");
		rideSeekerDTO.setAdharcard(533221234567L);
		rideSeekerDTO.setEmailId("manjureddy@cognizant.com");
		rideSeekerDTO.setPhone(9849669707L);
		rideSeekerDTO.setFirstName("ManjuReddy");
		rideSeekerDTO.setLastName("AKASHddy");
		rideSeekerDTO.setAddress("Kadapa");
		Status status = Status.Registered;
		rideSeekerDTO.setStatus(status);

		String rsId = Utility.generateRsId(rideSeekerDTO);
		rideSeekerDTO.setRsId(rsId);
		// Set the required properties of the rideSeekerDTO object

		ResponseEntity<RideSeekerDTO> response = rideSeekerRestController.createRideSeeker(rideSeekerDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		// Add additional assertions based on your requirements
	}

	@Test
	public void testUpdateRideSeeker() throws RideSeekerNotFoundException {
		@Valid
		RideSeeker rideSeeker = new RideSeeker();

		rideSeeker.setDateOfBirth("12/13/2222");
		rideSeeker.setAdharcard(533221234567L);
		rideSeeker.setEmailId("manjureddy@cognizant.com");
		rideSeeker.setPhone(9849669707L);
		rideSeeker.setFirstName("ManjuReddy");
		rideSeeker.setLastName("AKASHddy");
		rideSeeker.setAddress("Kadapa");
		Status status = Status.Registered;
		rideSeeker.setStatus(status);

		RideSeekerDTO rideSeekerDTO = RideSeekerMapper.mapToRideSeekerDTO(rideSeeker);
		String rsId = Utility.generateRsId(rideSeekerDTO);
		rideSeeker.setRsId(rsId);
		// Set the required properties of the rideSeeker object

		ResponseEntity<RideSeeker> response = rideSeekerRestController.updateRideSeeker(rsId, rideSeeker);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		// Add additional assertions based on your requirements
	}

	@Test
	public void testDeleteRideSeeker() {
		String id = "REVI06";

		ResponseEntity<String> response = rideSeekerRestController.deleteUser(id);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("The user is deleted", response.getBody());
		// Add additional assertions based on your requirements
	}

	@Test
	public void testUpdateRide() {
		String rsId = "REVI06";
		String tripId = "ES00RE";

		ResponseEntity<Boolean> response = rideSeekerRestController.updateRide(tripId);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertNotNull(response.getBody());
		// Add additional assertions based on your requirements
	}

	@Test
	public void testCreateRideSeeker_InvalidData() {
		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO();

		rideSeekerDTO.setDateOfBirth("12/13/2222");
		rideSeekerDTO.setAdharcard(533221234567L);
		rideSeekerDTO.setEmailId("manjureddy@cognizant.com");
		rideSeekerDTO.setPhone(9849669707L);
		rideSeekerDTO.setFirstName("ManjuReddy");
		rideSeekerDTO.setLastName("AKASHddy");
		rideSeekerDTO.setAddress("Kadapa");
		Status status = Status.Registered;
		rideSeekerDTO.setStatus(status);

		String rsId = Utility.generateRsId(rideSeekerDTO);
		rideSeekerDTO.setRsId(rsId);
		// Set the rideSeekerDTO properties with invalid or missing values

		ResponseEntity<RideSeekerDTO> response = rideSeekerRestController.createRideSeeker(rideSeekerDTO);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// Add additional assertions based on your requirements
	}

	@Test
	public void testUpdateRideSeeker_NotFound() throws RideSeekerNotFoundException {
		String updateRsId = "REMH00";
		RideSeeker rideSeeker = new RideSeeker();

		rideSeeker.setDateOfBirth("12/13/2222");
		rideSeeker.setAdharcard(533221234567L);
		rideSeeker.setEmailId("manjureddy@cognizant.com");
		rideSeeker.setPhone(9849669707L);
		rideSeeker.setFirstName("ManjuReddy");
		rideSeeker.setLastName("AKASHddy");
		rideSeeker.setAddress("Kadapa");
		Status status = Status.Registered;
		rideSeeker.setStatus(status);

		RideSeekerDTO rideSeekerDTO = RideSeekerMapper.mapToRideSeekerDTO(rideSeeker);
		String rsId = Utility.generateRsId(rideSeekerDTO);
		rideSeeker.setRsId(rsId);
		// Set the required properties of the rideSeeker object

		assertThrows(RideSeekerNotFoundException.class, () -> {
			rideSeekerRestController.updateRideSeeker(updateRsId, rideSeeker);
		});
	}

	@Test
	public void testBookARide_InvalidSearchCriteria() {
		BookRideDTO bookRideDTO = new BookRideDTO();
		bookRideDTO.setFromLoc("Bangalore");
		bookRideDTO.setToLoc("Chennai");
		bookRideDTO.setRideDate("2023-06-12");
		bookRideDTO.setNoOfRequiredSeats(2);
		// Set the bookRideDTO properties with invalid or missing values

		ResponseEntity<List<TripDTO>> response = rideSeekerRestController.bookARide(bookRideDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Add additional assertions based on your requirements
	}

	@Test
	public void testGetRideSeekerById_NotFound() {
		String rsId = "RETY29";

		ResponseEntity<RideSeekerDTO> response = rideSeekerRestController.getRideSeekerById(rsId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Add additional assertions based on your requirements
	}

	@Test
	void handleRideSeekerNotFoundExceptionTest() {
		// Create an instance of the exception
		RideSeekerNotFoundException exception = new RideSeekerNotFoundException("Ride seeker not found");

		// Create an instance of the GlobalExceptionHandler
		GlobalExceptionHandler handler = new GlobalExceptionHandler();

		// Invoke the handleRideSeekerNotFoundException method
		ResponseEntity<String> response = handler.handleRideSeekerNotFoundException(exception);

		// Assert the response status code and message
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Ride seeker not found", response.getBody());
	}

	@Test
	void handleConstraintValidationExceptionTest() {
		// Create a mock ConstraintViolationException
		ConstraintViolationException exception = Mockito.mock(ConstraintViolationException.class);
		ConstraintViolation<?> constraintViolation = Mockito.mock(ConstraintViolation.class);
		Set<ConstraintViolation<?>> constraintViolations = Collections.singleton(constraintViolation);

		// Configure the mock objects
		Mockito.when(exception.getConstraintViolations()).thenReturn(constraintViolations);
		Mockito.when(constraintViolation.getMessage()).thenReturn("Constraint violation message");

		// Create an instance of the GlobalExceptionHandler
		GlobalExceptionHandler handler = new GlobalExceptionHandler();

		// Invoke the handleConstraintValidationException method
		Object response = handler.handleConstraintValidationException(exception);

		// Assert the response
		assertTrue(response instanceof List<?>);
		List<?> responseList = (List<?>) response;
		assertEquals(1, responseList.size());
		assertEquals("Constraint violation message", responseList.get(0));
	}

}
