package com.iamgovindthakur.scheduled.service;

import java.util.Collection;

import com.iamgovindthakur.scheduled.model.Greeting;

public interface GreetingService {

	Collection<Greeting> findAll();

	Greeting findOneItem(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);

	void evictCache();

}
