package com.greenfoxacademy.herokutodo.controllers;

import com.greenfoxacademy.herokutodo.models.entities.Todo;
import com.greenfoxacademy.herokutodo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/todo")
public class TodoController {

  @Autowired
  ToDoRepository repo;

  @GetMapping(value = {"/h", "/list"})
  public String list(Model model){
    List<Todo> repoList = new ArrayList<>();
    String title = "";
    int id = 0;
    repo.findAll().forEach(repoList::add);
    model.addAttribute("repoList", repoList);
    model.addAttribute("title", title);
    model.addAttribute("id", id);
    return "home";
  }

  @GetMapping(value = "/")
  public String list(@RequestParam("id") Integer userId, Model model){
    List<Todo> repoList = new ArrayList<>();
    String title = "";
    int id = 0;
    repo.findAllByAssignee_Id(userId).forEach(repoList::add);
    model.addAttribute("repoList", repoList);
    model.addAttribute("title", title);
    model.addAttribute("id", id);
    return "home";
  }

 @GetMapping("/add")
  public String add(Model model){
    Todo todo = new Todo();
    model.addAttribute("todo", todo);
    return "view";
  }

  @PostMapping("/add")
  public ModelAndView add(Model model, @ModelAttribute Todo todo){
    repo.save(todo);
    return new ModelAndView("redirect:/todo/");
  }

  @PostMapping("/{id}/delete")
  public ModelAndView delete(@PathVariable("id") int id){
    repo.delete(id);
    return new ModelAndView("redirect:/todo/");
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {
    model.addAttribute("editTodo", repo.findOne(id));
    return "edit";
  }

  @PostMapping("/{id}/edit")
  public ModelAndView edit(@PathVariable("id") int id, @ModelAttribute Todo newTodo) {
    newTodo.setId(id);
    repo.save(newTodo);
    return new ModelAndView("redirect:/todo/");
  }

  @GetMapping("/search")
  public String searchTitle(@RequestParam("title") String searchedTitle, @RequestParam("id") Integer searchedId, Model model) {
    List<Todo> repoList = new ArrayList<>();
    repo.findAllByTitle(searchedTitle).forEach(repoList::add);
    repo.findAllById(searchedId).forEach(repoList::add);
    model.addAttribute("repoList", repoList);
    return "home";
  }
}
