package com.greenfoxacademy.herokutodo.repositories;

import com.greenfoxacademy.herokutodo.models.entities.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<Todo,Integer> {

  //by title
 List<Todo> findAllByTitle(String searchedTitle);

 //by id
  List<Todo> findAllById(Integer searchedId);

  //by assignee One-to-One
  List<Todo> findByAssignee(Integer searchedId);

  //by assignee Many-toOne
 List<Todo> findAllByAssignee_Id(Integer searchedId);

}
