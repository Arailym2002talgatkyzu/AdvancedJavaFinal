package com.example.demo.controllers;

import com.example.demo.models.QuestionEntity;
import com.example.demo.models.UserEntity;
import com.example.demo.repo.QuestionRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.QuestionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class GeneralController {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuestionRepo questionRepo;

    private final QuestionServer questionServer = new QuestionServer();

    @ModelAttribute("authority")
    public boolean authority() {
        return getCurrentUser().getEmail().contains("admin");
    }

    @RequestMapping("/home")
    public String home(Model model) {
        UserEntity user = getCurrentUser();

        model.addAttribute("userid", user.getUser_id());
        model.addAttribute("questionServer", questionServer);
        model.addAttribute("questions", questionRepo.findAll());

        return "home";
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request) {
        return "admin";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        UserEntity user = getCurrentUser();

        if (user.getUser_interest() != null) {
            model.addAttribute("interests", getInterestList(user.getUser_interest()));
        }

        model.addAttribute("user", user);

        return "profile";
    }

    private List<String> getInterestList(String interests) {
        List<String> interestList = new ArrayList<>();
        Collections.addAll(interestList, interests.split(" "));
        return interestList;
    }

    @RequestMapping("/profileEdit")
    public String profileEdit(Model model) {
        model.addAttribute("user", getCurrentUser());

        return "profileEdit";
    }

    @RequestMapping("/questionEdit")
    public String questionEdit(HttpServletRequest request, Model model) {
        QuestionEntity question = questionRepo.findById(Integer.parseInt(request.getParameter("id"))).get();
        model.addAttribute("question", question);

        return "questionEdit";
    }

    @RequestMapping("/log")
    public String log(HttpServletRequest request) {
        return "log";
    }

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepo.findByEmail(email);
    }
}
