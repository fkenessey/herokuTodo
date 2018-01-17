package com.greenfoxacademy.herokutodo.repositories;

import com.greenfoxacademy.herokutodo.models.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<Todo,Integer> {

  //by title
 List<Todo> findAllByTitle(String searchedTitle);

}
