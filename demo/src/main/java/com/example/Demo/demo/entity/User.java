package com.example.Demo.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "emailId",unique = true)
    private String emailId;
    @Column(name = "password")
    private String password;

    public User(String name, String emailId, String password) {
    this.name=name;
    this.emailId=emailId;
    this.password=password;
    }

}
