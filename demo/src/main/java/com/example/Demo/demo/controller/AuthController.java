package com.example.Demo.demo.controller;

import com.example.Demo.demo.entity.User;
import com.example.Demo.demo.model.TokenObject;
import com.example.Demo.demo.service.Siginupservice;
import com.example.Demo.demo.util.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;


@RestController
@RequestMapping(path = "api/")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private Siginupservice siginupservice;

    @PostMapping("signup")
    public ResponseEntity<?> signUp (@RequestBody  User user){
        User muser=siginupservice.Siginupservice(user);
        HashMap<String, String> map = new HashMap<>();
        if(muser!=null){
            map.put("refresh_token", jwtUtils.generateRefreshToken(muser));
            map.put("access_token", jwtUtils.generateAccessJwt(muser));
            map.put("status", "200");
            return ResponseEntity.ok(map);
        }
        map.put("error", "user Alredy exist");
        map.put("status", "409");
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody  User user){
        User muser= siginupservice.LoginService(user);
        HashMap<String, String> map = new HashMap<>();
        if(muser!=null){
            map.put("refresh_token", jwtUtils.generateRefreshToken(muser));
            map.put("access_token", jwtUtils.generateAccessJwt(muser));
            map.put("status", "200");
            return ResponseEntity.ok(map);
        }
        map.put("error", "user does not exist");
        map.put("status", "404");
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("validate")
    public ResponseEntity<?> validate(@RequestHeader(value = "Authorization") String token){
        String result= jwtUtils.verifyAccess(token);
        HashMap<String, String> map = new HashMap<>();
        if(Objects.equals(result, "ok")){
            map.put("valid", "true" );
            map.put("expired", "false" );
            map.put("status", "200");
            return ResponseEntity.ok(map);
        } else if (Objects.equals(result, "expire")) {
            map.put("valid", "true" );
            map.put("expired", "true" );
            map.put("status", "401");
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
        map.put("valid", "false" );
        map.put("status", "400");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("revoke")
    public ResponseEntity<?> revokeToken(@RequestHeader(value = "Authorization") String token) throws JsonProcessingException {
        String result= jwtUtils.verifyRefresh(token);
        HashMap<String, String> map = new HashMap<>();
        if(Objects.equals(result, "ok")){
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper om=new ObjectMapper();
            TokenObject tokenObject=om.readValue(payload, TokenObject.class);
            User muser=siginupservice.getUser(tokenObject.getEmailId());
            if (muser!=null){
                map.put("access_token", jwtUtils.generateAccessJwt(muser));
                return ResponseEntity.ok(map);
            }
        }
        map.put("valid", "false" );
        map.put("status", "400");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }


}
