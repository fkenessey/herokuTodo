package com.greenfoxacademy.herokutodo;

import com.greenfoxacademy.herokutodo.models.entities.Assignee;
import com.greenfoxacademy.herokutodo.models.entities.Todo;
import com.greenfoxacademy.herokutodo.repositories.AssigneeRepository;
import com.greenfoxacademy.herokutodo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HerokutodoApplication implements CommandLineRunner {

	@Autowired
	ToDoRepository repo;
	@Autowired
	AssigneeRepository assRepo;

	public static void main(String[] args) {
		SpringApplication.run(HerokutodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Assignee assignee1 = new Assignee("Kata", "kata@gmail.com");
		Assignee assignee2 = new Assignee("Zsuzsa", "zsuzsi@cmail.com");
		assRepo.save(assignee1);
		assRepo.save(assignee2);
		repo.save(new Todo("I have to learn Object Relational Mapping", assignee1));
		repo.save(new Todo("Finish first exercise", assignee1));
		repo.save(new Todo("Understand mySQL better", assignee2));

	}
}
