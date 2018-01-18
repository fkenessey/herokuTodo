package com.greenfoxacademy.herokutodo.repositories;

import com.greenfoxacademy.herokutodo.models.entities.Assignee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends CrudRepository<Assignee,Integer> {

  //by name
  List<Assignee> findAllByName(String searchedName);

  //by email
  List<Assignee> findAllByEmail(String searchedEmail);
}
