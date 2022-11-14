package com.example.ResourseOwner.service;

import com.example.ResourseOwner.dto.ValidationDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidatorService {

    public boolean verifyToken(String token){
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        String url = "http://localhost:8080/api/validate";
        try{
            ValidationDto response = restTemplate.exchange(url, HttpMethod.GET, request, ValidationDto.class).getBody();
            return true;
        }catch(Exception e){
            return  false;
        }
    }
}
