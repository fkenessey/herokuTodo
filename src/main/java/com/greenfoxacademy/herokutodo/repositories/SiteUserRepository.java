package com.greenfoxacademy.herokutodo.repositories;

import com.greenfoxacademy.herokutodo.models.entities.SiteUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteUserRepository extends CrudRepository<SiteUser, Integer> {

  List<SiteUser> findAllByUserName(String checkThisUserName);
}
