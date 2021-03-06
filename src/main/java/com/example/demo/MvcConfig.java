package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("log");

        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/profileEdit").setViewName("profileEdit");
        registry.addViewController("/questionEdit").setViewName("questionEdit");
       // registry.addViewController("/log").setViewName("log");
        registry.addViewController("/reg").setViewName("reg");
    }

}
