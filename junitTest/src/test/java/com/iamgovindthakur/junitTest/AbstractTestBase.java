package com.iamgovindthakur.junitTest;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iamgovindthakur.junitTest.service.GreetingService;

/**
 * The AbstractTest class is the parent of all JUnit test classes. This class
 * configures the test ApplicationContext and test runner environment.
 * 
 * @author iamgovindthakur
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTestBase {

	/**
	 * The Logger instance for all classes in the unit test framework.
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public GreetingService service;

}
