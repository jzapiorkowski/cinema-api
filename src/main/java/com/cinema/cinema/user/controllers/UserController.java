package com.cinema.cinema.user.controllers;

import com.cinema.cinema.user.dto.CreateUserDto;
import com.cinema.cinema.user.models.User;
import com.cinema.cinema.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@Valid CreateUserDto createUserDto, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return "/users/register";
        }
        User user = userService.registerUser(createUserDto);

        System.out.println(user);

        return "redirect:/screenings";
    }

    @GetMapping("/register")
    public String registerUserForm(Model model) {
        CreateUserDto createUserDto = new CreateUserDto();

        model.addAttribute("newUser", createUserDto);

        return "register";
    }
}
