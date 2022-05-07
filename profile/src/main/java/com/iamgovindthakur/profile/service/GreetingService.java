package com.iamgovindthakur.profile.service;

import java.util.Collection;

import com.iamgovindthakur.profile.model.Greeting;

public interface GreetingService {

	Collection<Greeting> findAll();

	Greeting findOneItem(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);

	void evictCache();

}
