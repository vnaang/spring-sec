package ru.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.models.User;
import ru.service.UserService;

import javax.annotation.Resource;
import javax.annotation.Resources;

@Controller
@ComponentScan("/src/main/resources/templates")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/main";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.removeUserById(user.getId());
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String showUser(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "main";
    }

    @GetMapping("/edit")
    @ResponseBody
    public User findOne(Long id) {
        return userService.getUserById(id);
    }
}
