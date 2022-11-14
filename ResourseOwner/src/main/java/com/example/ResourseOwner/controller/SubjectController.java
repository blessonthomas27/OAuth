package com.example.ResourseOwner.controller;


import com.example.ResourseOwner.entity.Subject;
import com.example.ResourseOwner.model.TokenObject;
import com.example.ResourseOwner.service.SubjectService;
import com.example.ResourseOwner.service.ValidatorService;
import com.example.ResourseOwner.util.JsonUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(path = "api/subject")
public class SubjectController {
    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private JsonUtility jsonUtility;
    @PostMapping
    public ResponseEntity<?> createSubject(@RequestHeader(value = "Authorization") String token, @RequestBody Subject subject){
        if(validatorService.verifyToken(token)) {

            if (subjectService.createSubject(subject) != null) {

                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



    @DeleteMapping
    public ResponseEntity<?> deleteSubject(@RequestHeader(value = "Authorization") String token){
        if(validatorService.verifyToken(token)) {
            // mistake how can u delete by token??
            if (subjectService.deleteSubject(token)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public ResponseEntity<?> getSubjects(@RequestHeader(value = "Authorization") String token){
        try{
            if(validatorService.verifyToken(token)) {
                String[] chunks = token.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String payload = new String(decoder.decode(chunks[1]));
                ObjectMapper om = new ObjectMapper();
                TokenObject tokenObject = om.readValue(payload, TokenObject.class);
                List<Subject> subject=subjectService.recommendedSubjects(tokenObject.getEmailId());
                return ResponseEntity.status(HttpStatus.OK).body(jsonUtility.toJsonString(subject));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
