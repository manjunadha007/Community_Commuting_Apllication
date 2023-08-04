package com.cts.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cts.dto.RideSeekerDTO;
import com.cts.entities.RideSeeker;
import com.cts.entities.Status;
import com.cts.mapper.RideSeekerMapper;
import com.cts.repos.RideSeekerRepository;

@DataJpaTest
public class RideSeekerRepositoryTests {

	@Autowired
	private RideSeekerRepository rideSeekerRepo;

	// test for save rideseeker operation
	@Test
	public void rideSeekerObject_save_retutnRideSeekerObject() {

		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO();
		rideSeekerDTO.setRsId("RENA34");
		rideSeekerDTO.setDateOfBirth("12/13/1234");
		rideSeekerDTO.setAdharcard(533245098762L);
		rideSeekerDTO.setEmailId("manjuu@cognizant.com");
		rideSeekerDTO.setPhone(9849669707L);
		rideSeekerDTO.setFirstName("manjuuu");
		rideSeekerDTO.setLastName("Nadha");
		rideSeekerDTO.setAddress("Lingal");
		Status status = Status.Registered;
		rideSeekerDTO.setStatus(status);

		RideSeeker rideSeeker = RideSeekerMapper.mapToRideSeeker(rideSeekerDTO);

		// save method
		RideSeeker savedRideSeeker = rideSeekerRepo.save(rideSeeker);

		assertThat(savedRideSeeker).isNotNull();
		assertThat(savedRideSeeker.getRsId()).isEqualTo("RENA34");
	}
	
	@Test
	public void rideSeekerObject_update_retunUpdatedRideSeeker() {
		RideSeekerDTO rideSeekerDTO = new RideSeekerDTO();
		rideSeekerDTO.setRsId("RENA34");
		rideSeekerDTO.setDateOfBirth("12/13/1234");
		rideSeekerDTO.setAdharcard(533245098762L);
		rideSeekerDTO.setEmailId("manjuu@cognizant.com");
		rideSeekerDTO.setPhone(9849669707L);
		rideSeekerDTO.setFirstName("manjuuu");
		rideSeekerDTO.setLastName("Nadha");
		rideSeekerDTO.setAddress("Lingal");
		Status status = Status.Registered;
		rideSeekerDTO.setStatus(status);
		
		RideSeeker rideSeeker = RideSeekerMapper.mapToRideSeeker(rideSeekerDTO);
		
		rideSeekerRepo.save(rideSeeker);
		
		RideSeeker savedRideSeeker =  rideSeekerRepo.findById("RENA34").get();
		
		savedRideSeeker.setEmailId("2263730@cognizant.com");
		savedRideSeeker.setPhone(9949669707L);
		
		RideSeeker updatedRideSeeker = rideSeekerRepo.save(savedRideSeeker);
		
		assertThat(updatedRideSeeker.getEmailId()).isEqualTo("2263730@cognizant.com");
		assertThat(updatedRideSeeker.getPhone()).isEqualTo(9949669707L);
	}
	

}
