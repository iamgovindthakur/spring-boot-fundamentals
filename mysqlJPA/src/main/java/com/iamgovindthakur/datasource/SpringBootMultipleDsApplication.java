package com.iamgovindthakur.datasource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iamgovindthakur.datasource.book.repository.BookRepository;
import com.iamgovindthakur.datasource.model.book.Book;

@SpringBootApplication
@RestController
public class SpringBootMultipleDsApplication {

	@Autowired
	private BookRepository bookRepository;

	@PostConstruct
	public void addData2DB() {
		bookRepository.saveAll(
				Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDsApplication.class, args);
	}
}
