package com.greenfoxacademy.herokutodo.controllers;

import com.greenfoxacademy.herokutodo.models.entities.Assignee;
import com.greenfoxacademy.herokutodo.repositories.AssigneeRepository;
import com.greenfoxacademy.herokutodo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/todo/assignee")
public class AssigneeController {

  @Autowired
  AssigneeRepository assRepo;
  @Autowired
  ToDoRepository toDoRepository;

  @GetMapping("")
  public String list(Model model){
    List<Assignee> assList = new ArrayList<>();
    String name = "";
    String email = "";
    assRepo.findAll().forEach(assList::add);
    model.addAttribute("assList", assList);
    model.addAttribute("name", name);
    model.addAttribute("email", email);
    return "assignee";
  }

  @GetMapping("/add")
  public String add(Model model){
    Assignee assignee = new Assignee();
    model.addAttribute("assignee", assignee);
    return "addassignee";
  }

  @PostMapping("/add")
  public ModelAndView add(Model model, @ModelAttribute Assignee assignee){
    assRepo.save(assignee);
    return new ModelAndView("redirect:/todo/assignee");
  }

  @PostMapping("/{id}/delete")
  public ModelAndView delete(@PathVariable("id") int id){
    assRepo.delete(id);
    return new ModelAndView("redirect:/todo/assignee");
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("editAssignee", assRepo.findOne(id));
    return "editassignee";
  }

  @PostMapping("/{id}/edit")
  public ModelAndView edit(@PathVariable("id") int id, @ModelAttribute Assignee newAssignee) {
    newAssignee.setId(id);
    assRepo.save(newAssignee);
    return new ModelAndView("redirect:/todo/assignee");
  }

  @GetMapping("/search")
  public String searchTitle(@RequestParam("name") String searchedName, @RequestParam("email") String searchedEmail, Model model) {
    List<Assignee> assList = new ArrayList<>();
    assRepo.findAllByName(searchedName).forEach(assList::add);
    assRepo.findAllByEmail(searchedEmail).forEach(assList::add);
    model.addAttribute("assList", assList);
    return "assignee";
  }

  @GetMapping("/{id}/todos")
  public String showTodos(@PathVariable("id") int id, Model model) {
    model.addAttribute("assignee", assRepo.findOne(id));
    model.addAttribute("allocatedTodos", toDoRepository.findAllByAssignee_Id(id));
    return "todosperassignee";
  }
}
