package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.dto.BookRideDTO;
import com.cts.dto.RideSeekerDTO;
import com.cts.dto.TripDTO;
import com.cts.entities.RideSeeker;
import com.cts.exceptions.RideSeekerNotFoundException;
import com.cts.service.RideSeekerService;
import com.cts.utility.Utility;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/rideSeeker")
public class RideSeekerRestController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RideSeekerService rideSeekerService;

	@PostMapping("/new") 
	public ResponseEntity<RideSeekerDTO> createRideSeeker(@Valid @RequestBody RideSeekerDTO rideSeekerDTO) {
		String generatedRsId = Utility.generateRsId(rideSeekerDTO);
		rideSeekerDTO.setRsId(generatedRsId);
		RideSeekerDTO seeker = rideSeekerService.createRideSeeker(rideSeekerDTO);
		System.out.println(seeker);
		return new ResponseEntity<>(seeker, HttpStatus.CREATED);
	}

	@PutMapping("/{rsId}/update")
	public ResponseEntity<RideSeeker> updateRideSeeker(@PathVariable String rsId, @RequestBody RideSeeker rideSeeker)
			throws RideSeekerNotFoundException {
		System.out.println(rsId);
		RideSeeker seeker = rideSeekerService.updateRideSeeker(rsId, rideSeeker);

		return new ResponseEntity<>(seeker, HttpStatus.OK);
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		rideSeekerService.deleteRideSeeker(id);
		return new ResponseEntity<>("The user is deleted", HttpStatus.OK);
	}

	@PostMapping("/bookRide")
	public ResponseEntity<List<TripDTO>> bookARide(@RequestBody BookRideDTO bookRideDTO) {

		List<TripDTO> tripsBySearchCriteria = rideSeekerService.getTripsBySearchCriteria(bookRideDTO);

		return new ResponseEntity<>(tripsBySearchCriteria, HttpStatus.OK);
	}

	@GetMapping("/{rsId}/getUserById")
	public ResponseEntity<RideSeekerDTO> getRideSeekerById(@PathVariable String rsId) {
		RideSeekerDTO seeker = rideSeekerService.getRideSeekerById(rsId);
		return new ResponseEntity<RideSeekerDTO>(seeker, HttpStatus.OK);
	}

	@PutMapping("/{rsId}/updatee")
	public ResponseEntity<Boolean> updateRide(@RequestBody String tripId) {
		ResponseEntity<Boolean> response = restTemplate.postForEntity("http://localhost:8080/api/tripmanager/update",tripId,
				Boolean.class);
		return new ResponseEntity<>(response.getBody(), HttpStatus.ACCEPTED);
	}

	
}
