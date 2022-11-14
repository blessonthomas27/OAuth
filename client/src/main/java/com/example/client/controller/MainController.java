package com.example.client.controller;

import com.example.client.model.Student;
import com.example.client.model.SubList;
import com.example.client.model.Subject;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api")
public class MainController {

    TemplateLoader loader = new ClassPathTemplateLoader("/templates");
    Handlebars handlebars = new Handlebars(loader);

    @GetMapping("/student")
    public ResponseEntity<?> getStudents(@CookieValue("access_token") String token){
        try{
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            HttpEntity<String> request = new HttpEntity<>(headers);
            String url = "http://localhost:8081/api/student";
            Student student = restTemplate.exchange(url, HttpMethod.GET, request, Student.class).getBody();
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/subject")
    public ResponseEntity<?> getSubjects(@RequestHeader(value = "Authorization") String token){
        try{
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            HttpEntity<String> request = new HttpEntity<>(headers);
            String url = "http://localhost:8081/api/subject";
            Object[] subject_list = restTemplate.exchange(url, HttpMethod.GET, request,Object[].class).getBody();

            return ResponseEntity.status(HttpStatus.OK).body(subject_list);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/subject")
    public ResponseEntity<?> deleteSubject(@RequestParam String id, @CookieValue("access_token") String token){
        try {
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            headers.add("id", id);
            HttpEntity<String> request = new HttpEntity<>(headers);
            String url = "http://localhost:8081/api/student";
            Object[] subject_list = restTemplate.exchange(url, HttpMethod.DELETE, request,Object[].class).getBody();

            return ResponseEntity.status(HttpStatus.OK).body(subject_list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping("/subject")
    public ResponseEntity<?> addSubject(@RequestParam String id, @CookieValue("access_token") String token){
        try {
            RestTemplate restTemplate=new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            headers.add("id", id);
            HttpEntity<String> request = new HttpEntity<>(headers);
            String url = "http://localhost:8081/api/student";
            Object[] subject_list = restTemplate.exchange(url, HttpMethod.PUT, request,Object[].class).getBody();

            return ResponseEntity.status(HttpStatus.OK).body(subject_list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
