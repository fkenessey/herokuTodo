package com.greenfoxacademy.herokutodo.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "siteUsers")
@Getter
@Setter
public class SiteUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String userName;
  private String userPassword;

  public SiteUser() {
  }

  public SiteUser(String userName, String userPassword) {
    this.userName = userName;
    this.userPassword = userPassword;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
}
