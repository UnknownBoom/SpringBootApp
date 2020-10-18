package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.User;
import com.KpApp.SpringBootApp.repo.OrderRepo;
import com.KpApp.SpringBootApp.repo.UserRepo;
import com.KpApp.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class MainController {
    private UserRepo usersRepo;

    @Autowired
    private OrderRepo ordersRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;


    public MainController(UserRepo usersRepo) {
        this.usersRepo = usersRepo;
    }


    @GetMapping("/user_profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model){
        User userWithOrders = userService.getUserWithOrders(user);
        model.addAttribute("user",userWithOrders);
        return "user_profile";
    }
    @GetMapping("/")
    public String mainPage(Model model){
        return "mainPage";
    }

    @PostMapping("/profile_edit")
    public String editUser(@AuthenticationPrincipal User user_origin,
                           @RequestParam Long id,
                           @RequestParam String username,
                           @RequestParam(required = false) String password,
                           @RequestParam String first_name,
                           @RequestParam String surname,
                           @RequestParam String patronymic,
                           Model model){
        userService.editUser(user_origin,id,username,password,first_name,surname,patronymic,model);
        model.addAttribute("user",usersRepo.findUsersById(user_origin.getId()));
        return "user_profile";
    }

}
