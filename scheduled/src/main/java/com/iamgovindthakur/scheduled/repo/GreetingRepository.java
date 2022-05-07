package com.iamgovindthakur.scheduled.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamgovindthakur.scheduled.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	

}
