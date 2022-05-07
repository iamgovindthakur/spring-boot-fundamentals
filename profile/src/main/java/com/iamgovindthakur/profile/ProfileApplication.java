package com.iamgovindthakur.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.iamgovindthakur.profile.model.Greeting;
import com.iamgovindthakur.profile.repo.GreetingRepository;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class ProfileApplication implements CommandLineRunner {

	@Autowired
	GreetingRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
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
