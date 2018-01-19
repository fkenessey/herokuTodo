package com.greenfoxacademy.herokutodo.controllers;

import com.greenfoxacademy.herokutodo.models.entities.Assignee;
import com.greenfoxacademy.herokutodo.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteUserController {

  @Autowired
  AssigneeRepository siteUserRepository;

  @GetMapping("")
  public String loginPage(Model model) {
    Assignee loginSiteUser = new Assignee();
    model.addAttribute("siteUser", loginSiteUser);
    return "loginmap/loginpage";
  }

  @PostMapping("/login")
  public String checkLogin(@ModelAttribute("siteUser") Assignee loginSiteUser, Model model) {
    List<Assignee> registeredUser = new ArrayList<>();

    siteUserRepository.findAllByUserName(loginSiteUser.getUserName()).forEach(registeredUser::add);

    if (registeredUser.size() == 0) {
      return "redirect:/login/create";
    } else if (registeredUser.get(0).getUserPassword().equals(loginSiteUser.getUserPassword())) {
      return "redirect:/todo/";
    } else if (registeredUser.get(0).getUserName().equals(loginSiteUser.getUserName())){
      return "redirect:/login/forgottenpassword";
    } else {
      return "redirect:/login/create";
    }
  }

  @GetMapping("/login/create")
  public String createLogin(Model model) {
    Assignee newSiteUser = new Assignee();
    model.addAttribute("newSiteUser", newSiteUser);
    return "loginmap/createlogin";
  }

  @PostMapping("/login/create")
  public String createLogin(@ModelAttribute("newSiteUser") Assignee newSiteUser) {
    List<Assignee> checkList = new ArrayList<>();
    siteUserRepository.findAllByUserName(newSiteUser.getUserName()).forEach(checkList::add);
    if (checkList.size() == 0) {
      siteUserRepository.save(newSiteUser);
      return "redirect:/todo/";
    } else {
      return "redirect:/login/forgottenpassword";
    }
  }

  @GetMapping("/login/forgottenpassword")
  public String forgottenLogin() {
    return "loginmap/forgottenlogin";
  }
}
