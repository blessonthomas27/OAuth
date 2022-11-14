package com.example.ResourseOwner.controller;


import com.example.ResourseOwner.dto.UpdateDto;
import com.example.ResourseOwner.entity.Student;
import com.example.ResourseOwner.model.TokenObject;
import com.example.ResourseOwner.service.StudentService;
import com.example.ResourseOwner.service.ValidatorService;
import com.example.ResourseOwner.util.JsonUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JsonUtility jsonUtility;

    @PostMapping
    private ResponseEntity<?> createStudent(@RequestHeader(value = "Authorization") String token) throws JsonProcessingException {
        if(validatorService.verifyToken(token)) {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper om=new ObjectMapper();
            TokenObject tokenObject=om.readValue(payload,TokenObject.class);
            Student student=new Student();
            student.setEmailId(tokenObject.getEmailId());
            student.setName(tokenObject.getName());
            if(studentService.createStudent(student)!=null){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping
    private ResponseEntity<?> updateSubject(@RequestHeader(value = "Authorization") String token,@RequestHeader(value = "id") Long id) throws JsonProcessingException {
        if(validatorService.verifyToken(token)) {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper om=new ObjectMapper();
            TokenObject tokenObject=om.readValue(payload,TokenObject.class);
            if(studentService.addSubject(id,tokenObject.getEmailId())!=null){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else {
            System.out.println(token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @DeleteMapping
    private  ResponseEntity<?> deleteSubject(@RequestHeader(value = "Authorization") String token,@RequestHeader(value = "id") Long id) {
        try {
            if (validatorService.verifyToken(token)) {
                String[] chunks = token.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String payload = new String(decoder.decode(chunks[1]));
                ObjectMapper om = new ObjectMapper();
                TokenObject tokenObject = om.readValue(payload, TokenObject.class);
                if (studentService.removeSubject(id, tokenObject.getEmailId()) != null) {
                    return ResponseEntity.status(HttpStatus.OK).build();
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping
    private ResponseEntity<?> getDetails(@RequestHeader(value = "Authorization") String token){
        try{
            if(validatorService.verifyToken(token)){
                String[] chunks = token.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String payload = new String(decoder.decode(chunks[1]));
                ObjectMapper om = new ObjectMapper();
                TokenObject tokenObject = om.readValue(payload, TokenObject.class);
                Student student=studentService.getDetails(tokenObject.getEmailId());
                if (student != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(jsonUtility.toJsonString(student));
                } else {
                    Student new_student=new Student();
                    new_student.setEmailId(tokenObject.getEmailId());
                    new_student.setName(tokenObject.getName());
                    Student m_new_student=studentService.createStudent(new_student);
                    if(m_new_student!=null){
                        return ResponseEntity.status(HttpStatus.OK).body(jsonUtility.toJsonString(m_new_student));
                    }

                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
