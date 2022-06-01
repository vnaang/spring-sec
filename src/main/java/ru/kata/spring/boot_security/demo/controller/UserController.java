package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String startPage(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";

    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "createUser";

    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if (isNull(user.getId())) {
            userService.add(user);
        }
        return "redirect:/users/";
    }

    @GetMapping(value = "/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userForms";

    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users/";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user) {
        if (nonNull(user.getId())) {
            userService.updateUser(user);
        }
        return "redirect:/users/";
    }
}
