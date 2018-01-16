package com.greenfoxacademy.herokutodo;

import com.greenfoxacademy.herokutodo.models.Todo;
import com.greenfoxacademy.herokutodo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HerokutodoApplication implements CommandLineRunner {

	@Autowired
	ToDoRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(HerokutodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repo.save(new Todo("I have to learn Object Relational Mapping"));
		repo.save(new Todo("Finish first exercise"));
		repo.save(new Todo("Understand mySQL better"));
	}
}
