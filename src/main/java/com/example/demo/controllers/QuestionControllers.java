package com.example.demo.controllers;

import com.example.demo.models.QuestionEntity;
import com.example.demo.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/question")
public class QuestionControllers {
    @Autowired
    QuestionRepo questionRepo;

    @ModelAttribute("allQuestion")
    public Iterable<QuestionEntity> showAll() {
        return questionRepo.findAll();
    }

    @PostMapping("add")
    public RedirectView add(HttpServletRequest request) {
        QuestionEntity question = new QuestionEntity(
                request.getParameter("question"),
                request.getParameter("ans1"),
                request.getParameter("ans2"),
                0,
                " ",
                " "
        );
        questionRepo.save(question);

        return new RedirectView("/home");
    }

    @PostMapping("edit")
    public RedirectView edit(HttpServletRequest request) {
        QuestionEntity question = new QuestionEntity(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("question"),
                request.getParameter("ans1"),
                request.getParameter("ans2"),
                Integer.parseInt(request.getParameter("count")),
                request.getParameter("ans1_count"),
                request.getParameter("ans2_count")
        );
        questionRepo.save(question);

        return new RedirectView("/home");
    }

    @GetMapping("tapAns1")
    public RedirectView tapAns1(HttpServletRequest request) {
        Integer questionId = Integer.parseInt(request.getParameter("id"));
        Integer userId = Integer.parseInt(request.getParameter("user_id"));

        QuestionEntity question = questionRepo.findById(questionId).get();

        String tapCount = (question.getAns1_count() == null) ? "" : question.getAns1_count();
        question.setAns1_count(tapCount + " " + userId);

        question.setCount((question.getCount() == null) ? 1 : question.getCount() + 1);
        //  question.setCount(question.getCount()+1);
        questionRepo.save(question);

        return new RedirectView("/home");
    }

    @GetMapping("tapAns2")
    public RedirectView tapAns2(HttpServletRequest request) {
        Integer questionId = Integer.parseInt(request.getParameter("id"));
        Integer userId = Integer.parseInt(request.getParameter("user_id"));

        QuestionEntity question = questionRepo.findById(questionId).get();

        String tapCount = (question.getAns2_count() == null) ? "" : question.getAns2_count();
        question.setAns2_count(tapCount + " " + userId);
        //         question.setCount(question.getCount()+1);
        Integer generalCount = (question.getCount() == null) ? 0 : question.getCount() + 1;
        question.setCount(generalCount);

        questionRepo.save(question);

        return new RedirectView("/home");
    }

    @GetMapping("remove")
    public RedirectView remove(HttpServletRequest request) {
        questionRepo.deleteById(Integer.valueOf(request.getParameter("id")));

        return new RedirectView("/home");
    }
}

