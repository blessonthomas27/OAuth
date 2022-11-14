package com.example.Demo.demo.controller;

import com.example.Demo.demo.entity.Client;
import com.example.Demo.demo.service.ClientService;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class HomeController {
    @Autowired
    private ClientService clientService;
    TemplateLoader lod = new ClassPathTemplateLoader("/templates");
    Handlebars handlebars = new Handlebars(lod);

    @GetMapping("/login")
    public String  login (@RequestParam(name = "clientid") Long clientid, Model model) throws IOException {
        Client client=clientService.getClient(clientid);
        if(client!=null){
            Template template =handlebars.compile("login");
            model.addAttribute("domainName",client.getDomainName());
            model.addAttribute("clientid",client.getId());
            return template.apply(model);
        }
        Template template =handlebars.compile("broken");
        model.addAttribute("error","no client found !");
        return template.apply(model);
    }

    @GetMapping("/signup")
    public String studentSignup (@RequestParam(name = "clientid") Long clientid, Model model) throws IOException {
        Client client=clientService.getClient(clientid);
        if(client!=null){
            Template template =handlebars.compile("signup");
            model.addAttribute("domainName",client.getDomainName());
            model.addAttribute("clientid",client.getId());
            return template.apply(model);
        }
        Template template =handlebars.compile("broken");
        model.addAttribute("error","no client found !");
        return template.apply(model);
    }
}
