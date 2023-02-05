package com.web.myquiz.controller;

import com.web.myquiz.domain.User;
import com.web.myquiz.service.LoginService;
import com.web.myquiz.service.UserService;
import com.web.myquiz.service.impl.LoginServiceImpl;
import com.web.myquiz.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    private LoginService loginService;
    private UserService userService;

    @Autowired
    public LoginController(LoginServiceImpl loginServiceImpl, UserServiceImpl userServiceImpl) {
        this.loginService = loginServiceImpl;
        this.userService = userServiceImpl;
    }


    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // redirect to /home if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam String username,
                        @RequestParam String password) {

        Optional<User> user = loginService.validateUser(username, password);
        if(user.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null)
                oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", user.get());
            if(user.get().is_admin())
                return "redirect:/homeAdmin";
            else
                return "redirect:/home";
        } else {
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }


    @GetMapping("/register")
    public String getRegisterPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // redirect to /home if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "register";
    }


    @PostMapping("/register")
    public String signUp(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String firstname,
                         @RequestParam String lastname,
                         HttpServletRequest request) {
        userService.createUser(username, password, firstname, lastname);
        User user = loginService.validateUser(username, password).get();
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        return "redirect:/home";
    }





}
