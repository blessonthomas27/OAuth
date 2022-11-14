package com.example.ResourseOwner.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtility {

    public static String toJsonString(Object obj) {
        String jsonString="";
        ObjectMapper objectMapper=new ObjectMapper();

        try {
            jsonString=objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static <I> I toObject(String jsonstring,Class<I> classObj) {
        I result=null;
        ObjectMapper objectMapper =new ObjectMapper();
        try {
            result=objectMapper.readValue(jsonstring,classObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public   JsonUtility(){

    }
}