package com.example.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubList {
    private List<Subject> subjects;

    @Override
    public String toString() {
        return "SubList{" +
                "subjects=" + subjects +
                '}';
    }
}