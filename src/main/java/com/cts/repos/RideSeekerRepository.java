package com.cts.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entities.RideSeeker;

@Repository
public interface RideSeekerRepository extends JpaRepository<RideSeeker, String> {

}
