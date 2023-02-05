package com.web.myquiz.controller;

import com.web.myquiz.domain.User;
import com.web.myquiz.service.UserService;
import com.web.myquiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }



    @GetMapping("/account/{user_id}")
    public String getUserAccount(@PathVariable int user_id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        User user = userService.getUserById(user_id).get();
        if (!user.equals(session.getAttribute("user")))
            session.setAttribute("user", user);
        return "account";
    }

    @PostMapping("/account/{user_id}")
    public String updateUserAccount(@PathVariable int user_id,
                           @RequestParam String password,
                           @RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam String address,
                           @RequestParam String email,
                           @RequestParam String phone,
                           HttpServletRequest request) {
        userService.updateUserAccount(user_id, password, firstname, lastname, address, email, phone);

        return "redirect:/account/"+user_id;
    }

    @GetMapping("/account/setting")
    public String getModifyAccountPage(HttpServletRequest request) {
        return "modifyAccount";
    }



}
