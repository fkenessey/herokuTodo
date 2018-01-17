package com.greenfoxacademy.herokutodo.controllers;


import com.greenfoxacademy.herokutodo.models.Todo;
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

  @RequestMapping(value = {"/", "/list"})
  public String list(Model model, @RequestParam(value = "isActive", required = false) boolean isActive){
    List<Todo> repoList = new ArrayList<>();
    String title = "";
    repo.findAll().forEach(repoList::add);
    if (isActive){
      for (int i = 0; i < repoList.size(); i++) {
        if (repoList.get(i).getIsDone()){
          repoList.remove(i);
        }
      }
    }
    model.addAttribute("repoList", repoList);
    model.addAttribute("title", title);
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

  @GetMapping("/search/{title}")
  public String searchTitle(@PathVariable("title") String searchedTitle, Model model) {
    List<Todo> repoList = new ArrayList<>();
    repo.findAllByTitle(searchedTitle).forEach(repoList::add);
    model.addAttribute("repoList", repoList);
    return "home";
  }

}
