package com.iamgovindthakur.transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iamgovindthakur.transactional.model.Greeting;
import com.iamgovindthakur.transactional.service.GreetingService;

@SpringBootTest
class TransactionalApplicationTests {

	@Autowired
	private GreetingService service;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void testAddUser() {
		service.create(new Greeting("Hello India"));
	}

}
