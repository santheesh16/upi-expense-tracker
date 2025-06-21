package com.personal.upi_expense_tracker.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet(HttpServletRequest request){
        System.out.println("Hello");
        return "Welcome to UPI Expense Tracker: " + request.getSession().getId();
    }
}
