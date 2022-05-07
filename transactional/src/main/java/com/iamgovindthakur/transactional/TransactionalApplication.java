package com.iamgovindthakur.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.iamgovindthakur.transactional.model.Greeting;
import com.iamgovindthakur.transactional.repo.GreetingRepository;

@SpringBootApplication
@EnableTransactionManagement
public class TransactionalApplication implements CommandLineRunner {

	@Autowired
	GreetingRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(TransactionalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repo.save(new Greeting("Hello World"));
		repo.save(new Greeting("Hello India"));
		repo.save(new Greeting("Hello USA"));
		repo.save(new Greeting("Hello UK"));

	}

}
