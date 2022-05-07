package com.iamgovindthakur.junitTest.rest;

import java.util.Collection;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iamgovindthakur.junitTest.model.Greeting;
import com.iamgovindthakur.junitTest.service.EmailService;
import com.iamgovindthakur.junitTest.service.GreetingService;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	@Autowired
	private EmailService emailService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(

			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {

		logger.info("> getGreetings");

		Collection<Greeting> greetings = greetingService.findAll();

		logger.info("< getGreetings");

		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {

		logger.info("> getGreeting id:{}", id);
		Greeting greeting = greetingService.findOneItem(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

		logger.info("< getGreeting id:{}", id);
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(

			method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {

		logger.info("> createGreeting");

		Greeting savedGreeting = greetingService.create(greeting);

		logger.info("< createGreeting");

		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {

		logger.info("> updateGreeting id:{}", greeting.getId());

		Greeting updatedGreeting = greetingService.update(greeting);
		if (updatedGreeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< updateGreeting id:{}", greeting.getId());

		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting) {

		logger.info("> deleteGreeting id:{}", id);

		greetingService.delete(id);

		logger.info("< deleteGreeting id:{}", id);

		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sendGreeting(@PathVariable("id") Long id,
			@RequestParam(value = "wait", defaultValue = "false") boolean waitForAsyncResult) {

		logger.info("> sendGreeting id:{}", id);

		Greeting greeting = null;

		try {
			greeting = greetingService.findOneItem(id);
			if (greeting == null) {
				logger.info("< sendGreeting id:{}", id);
				return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
			}

			if (waitForAsyncResult) {
				Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(greeting);
				boolean emailSent = asyncResponse.get();
				logger.info("- greeting email sent? {}", emailSent);
			} else {
				emailService.sendAsync(greeting);
			}
		} catch (Exception e) {
			logger.error("A problem occurred sending the Greeting.", e);
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("< sendGreeting id:{}", id);
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

}