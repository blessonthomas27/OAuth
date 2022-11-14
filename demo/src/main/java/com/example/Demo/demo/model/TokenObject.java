package com.example.Demo.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenObject {
    private String iss;
    private String iat;
    private String exp;
    private String name;
    private String emailId;
    private String id;
    private String type;
}

