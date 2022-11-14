package com.example.Demo.demo.service;

import com.example.Demo.demo.entity.User;
import com.example.Demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@Service
public class Siginupservice {

    @Autowired
    private UserRepository userRepository;

    public User Siginupservice(User user){
        try{
            System.out.println(userRepository.save(user));
        }catch (Exception e){
            return null;
        }
        return user;
    }

    public User LoginService(User user){
        try{
            User muser =userRepository.findByemailId(user.getEmailId());
            if(Objects.equals(user.getPassword(), muser.getPassword())){
                return muser;
            }
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public User getUser(String emailId){
        try{
            return userRepository.findByemailId(emailId);
        }catch (NullPointerException e){
            return null;
        }
    }

}
