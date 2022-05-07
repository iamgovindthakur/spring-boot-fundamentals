package com.iamgovindthakur.async.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamgovindthakur.async.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	

}
