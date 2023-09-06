package com.rawt.authserver.controllers;

import com.rawt.authserver.model.ShopUser;
import com.rawt.authserver.repos.ShopUserRepository;
import com.rawt.authserver.svc.ShopUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final ShopUserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new ShopUser());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@RequestParam("password") String password,
                           @RequestParam("matchingPassword") String matchingPassword,
                           @ModelAttribute("user") ShopUser user) {
        user.setRoles(Arrays.asList("ROLE_USER"));
        if(userService.findByEmail(user.getEmail()) != null) {
            return "redirect:/register?error";
        }
        if(user.getPassword().equals(user.getMatchingPassword())) {
            userService.save(user);
            return "redirect:/login";
        } else {
            return "redirect:/register?error";
        }
    }
}
