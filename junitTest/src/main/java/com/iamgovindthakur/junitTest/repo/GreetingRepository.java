package com.iamgovindthakur.junitTest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamgovindthakur.junitTest.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	

}
