package com.greenfoxacademy.herokutodo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "assignees")
@Getter
@Setter
public class Assignee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;
  private String email;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private Todo todo;

  public Assignee() {
  }

  public Assignee(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
