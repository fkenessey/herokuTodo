package com.greenfoxacademy.herokutodo.controllers;

import com.greenfoxacademy.herokutodo.models.entities.SiteUser;
import com.greenfoxacademy.herokutodo.repositories.SiteUserRepository;
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
  SiteUserRepository siteUserRepository;

  @GetMapping("")
  public String loginPage(Model model) {
    SiteUser loginSiteUser = new SiteUser();
    model.addAttribute("siteUser", loginSiteUser);
    return "loginmap/loginpage";
  }

  @PostMapping("/login")
  public String checkLogin(@ModelAttribute("siteUser") SiteUser loginSiteUser, Model model) {
    List<SiteUser> registeredUser = new ArrayList<>();

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
    SiteUser newSiteUser = new SiteUser();
    model.addAttribute("newSiteUser", newSiteUser);
    return "loginmap/createlogin";
  }

  @PostMapping("/login/create")
  public String createLogin(@ModelAttribute("newSiteUser") SiteUser newSiteUser) {
    List<SiteUser> checkList = new ArrayList<>();
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
