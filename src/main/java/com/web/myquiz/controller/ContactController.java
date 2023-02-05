package com.web.myquiz.controller;

import com.web.myquiz.domain.User;

import com.web.myquiz.service.ContactService;
import com.web.myquiz.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactServiceImpl contactServiceImpl) {
        this.contactService = contactServiceImpl;
    }

    @GetMapping("/contact")
    public String getContactUsPage() {
        return "contact";
    }

    @PostMapping("/contact")
    public String sendContactForm(@RequestParam String subject,
                                  @RequestParam String message,
                                  HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        contactService.createContact(user.getUser_id(), subject, message);
        return "contact";
    }

}
