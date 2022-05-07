package com.iamgovindthakur.junitTest.service;

import java.util.Collection;

import com.iamgovindthakur.junitTest.model.Greeting;

public interface GreetingService {

	Collection<Greeting> findAll();

	Greeting findOneItem(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);

	void evictCache();

}
