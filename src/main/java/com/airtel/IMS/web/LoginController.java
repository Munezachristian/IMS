package com.airtel.IMS.web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    private static final String SYSADMIN_USERNAME = "24RP12634";
    private static final String SYSADMIN_PASSWORD = "24RP03282";
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        HttpSession session) {
        if (SYSADMIN_USERNAME.equals(username) && SYSADMIN_PASSWORD.equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/";
        }
        return "redirect:/login?error";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}