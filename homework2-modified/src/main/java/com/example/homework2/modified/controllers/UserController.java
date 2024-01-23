package com.example.homework2.modified.controllers;

import com.example.homework2.modified.model.User;
import com.example.homework2.modified.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        log.info("Список юзеров возвращен");
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        log.info("Новый пользователь добавлен");
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        log.info("Пользователь удален из списка");
        return "redirect:/users";
    }
}
