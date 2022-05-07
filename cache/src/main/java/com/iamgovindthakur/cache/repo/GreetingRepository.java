package com.iamgovindthakur.cache.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamgovindthakur.cache.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

	

}
