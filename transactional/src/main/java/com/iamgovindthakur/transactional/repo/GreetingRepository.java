package com.iamgovindthakur.transactional.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamgovindthakur.transactional.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	

}
