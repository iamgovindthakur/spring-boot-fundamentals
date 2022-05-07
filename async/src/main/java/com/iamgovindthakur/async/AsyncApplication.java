package com.iamgovindthakur.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.iamgovindthakur.async.model.Greeting;
import com.iamgovindthakur.async.repo.GreetingRepository;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
public class AsyncApplication implements CommandLineRunner {

	@Autowired
	GreetingRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(AsyncApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager()
	{
		
		GuavaCacheManager cacheManager=new GuavaCacheManager("greetings");
		return cacheManager;
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		repo.save(new Greeting("Hello World"));
		repo.save(new Greeting("Hello India"));
		repo.save(new Greeting("Hello USA"));
		repo.save(new Greeting("Hello UK"));

	}

}
