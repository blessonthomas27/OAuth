package com.example.ResourseOwner.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subject_name",nullable = false,unique = true)
    private String subjectname;
    @Column(name = "course_duration")
    private String courseDuration;
    @Column(name = "credit")
    private String credit;
    @Column(name = "price")
    private String price;

    //manyTomany
    @ManyToMany(mappedBy = "subjects")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();

    public Subject() {
    }

    public Subject(String subjectname, String courseDuration, String credit, String price) {
    this.subjectname=subjectname;
    this.courseDuration=courseDuration;
    this.credit=credit;
    this.price=price;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
