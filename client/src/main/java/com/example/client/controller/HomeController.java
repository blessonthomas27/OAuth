package com.example.client.controller;

import com.example.client.model.Student;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class HomeController {

    TemplateLoader loader = new ClassPathTemplateLoader("/templates");
    Handlebars handlebars = new Handlebars(loader);

    @GetMapping("/auth")
    public String auth(Model model) throws IOException {
        Template template =handlebars.compile("auth");
        model.addAttribute("clientid",1);
        return template.apply(model);
    }

}