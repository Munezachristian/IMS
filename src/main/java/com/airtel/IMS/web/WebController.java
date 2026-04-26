package com.airtel.IMS.web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
    
    @GetMapping("/")
    public String index(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "index";
    }
    
    @GetMapping("/devices")
    public String devices(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "devices";
    }
    
    @GetMapping("/employees")
    public String employees(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "employees";
    }
    
    @GetMapping("/assignments")
    public String assignments(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "assignments";
    }
    
    @GetMapping("/issues")
    public String issues(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "issues";
    }
    
    @GetMapping("/reports")
    public String reports(HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/login";
        return "report";
    }
}