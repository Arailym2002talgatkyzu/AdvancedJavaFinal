package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;
    private String ans1;
    private String ans2;
    private Integer count;
    private String ans1_count;
    private String ans2_count;

    public QuestionEntity() {
    }

    public QuestionEntity(String question, String ans1, String ans2, Integer count, String ans1_count, String ans2_count) {
        setQuestion(question);
        setAns1(ans1);
        setAns2(ans2);
        setCount(count);
        setAns1_count(ans1_count);
        setAns2_count(ans2_count);
    }

    public QuestionEntity(Integer id, String question, String ans1, String ans2, Integer count, String ans1_count, String ans2_count) {
        setId(id);
        setQuestion(question);
        setAns1(ans1);
        setAns2(ans2);
        setCount(count);
        setAns1_count(ans1_count);
        setAns2_count(ans2_count);
    }

    //getter
    public Integer getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public Integer getCount() {
        return count;
    }

    public String getAns1_count() {
        return ans1_count;
    }

    public String getAns2_count() {
        return ans2_count;
    }

    //setter
    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setAns1_count(String ans1_count) {
        this.ans1_count = ans1_count;
    }

    public void setAns2_count(String ans2_count) {
        this.ans2_count = ans2_count;
    }
}
