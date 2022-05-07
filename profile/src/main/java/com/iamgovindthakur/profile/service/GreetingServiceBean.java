package com.iamgovindthakur.profile.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iamgovindthakur.profile.model.Greeting;
import com.iamgovindthakur.profile.repo.GreetingRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	@Override
	public Collection<Greeting> findAll() {

		Collection<Greeting> greetings = greetingRepository.findAll();

		return greetings;
	}

	@Override
	@Cacheable(value = "greetings", key = "#id")
	public Greeting findOneItem(Long id) {

		Optional<Greeting> greeting = greetingRepository.findById(id);

		return greeting.get();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#result.id")
	public Greeting create(Greeting greeting) {

		// Ensure the entity object to be created does NOT exist in the
		// repository. Prevent the default behavior of save() which will update
		// an existing entity if the entity matching the supplied id exists.
		if (greeting.getId() != null) {
			// Cannot create Greeting with specified ID value
			return null;
		}

		Greeting savedGreeting = greetingRepository.save(greeting);

		// Illustrate Tx Rollback
		if (savedGreeting.getId() == 6L) {
			throw new RuntimeException("Roll me back!");
		}

		return savedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetings", key = "#greeting.id")
	public Greeting update(Greeting greeting) {

		// Ensure the entity object to be updated exists in the repository to
		// prevent the default behavior of save() which will persist a new
		// entity if the entity matching the id does not exist
		Greeting greetingToUpdate = findOneItem(greeting.getId());
		if (greetingToUpdate == null) {
			// Cannot update Greeting that hasn't been persisted
			return null;
		}

		greetingToUpdate.setText(greeting.getText());
		Greeting updatedGreeting = greetingRepository.save(greetingToUpdate);

		return updatedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "greetings", key = "#id")
	public void delete(Long id) {

		greetingRepository.deleteById(id);

	}

	@Override
	@CacheEvict(value = "greetings", allEntries = true)
	public void evictCache() {

	}

}
