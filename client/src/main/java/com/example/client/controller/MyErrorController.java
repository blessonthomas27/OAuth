package com.example.client.controller;


import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MyErrorController implements ErrorController {

    TemplateLoader loader = new ClassPathTemplateLoader("/templates");
    Handlebars handlebars = new Handlebars(loader);

    @RequestMapping("/error")
    public String handleError(Model model) throws IOException {
        Template template =handlebars.compile("broken");
        model.addAttribute("clientid",1);
        return template.apply(model);
    }
}