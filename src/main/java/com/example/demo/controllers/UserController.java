package com.example.demo.controllers;

import com.example.demo.models.UserEntity;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("add")
    public RedirectView add(HttpServletRequest request) {

        UserEntity user = new UserEntity(
                request.getParameter("fname"),
                request.getParameter("group"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("interest"),
                request.getParameter("email"),
                passwordEncoder.encode(request.getParameter("password"))
        );
        userRepo.save(user);


        return new RedirectView("/home");
    }

    @PostMapping("edit")
    public RedirectView edit(HttpServletRequest request) {

        UserEntity user = new UserEntity(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("fname"),
                (request.getParameter("group") == null) ? request.getParameter("prev_group") : request.getParameter("group"),
                (request.getParameter("age") == null) ? null : Integer.parseInt(request.getParameter("age")),
                (request.getParameter("interest") == null) ? request.getParameter("prev_interest") : request.getParameter("interest"),
                Boolean.parseBoolean(request.getParameter("enabled")),
                request.getParameter("email"),
                //if no new password, then previous password will be not changed, else chnage to new password
                (request.getParameter("password") == null) ? request.getParameter("prev_password") : passwordEncoder.encode(request.getParameter("password"))

        );
        userRepo.save(user);

        return new RedirectView("/profile");
    }
}
