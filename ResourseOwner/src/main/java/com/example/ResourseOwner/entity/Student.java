package com.example.ResourseOwner.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "student")
public class Student {
    public Student() {
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "emailId",unique = true)
    private String emailId;

    //manyTomany

    @ManyToMany
    @JoinTable(
            name = "Student_Subject",
            joinColumns = @JoinColumn(name = "Student_id"),
            inverseJoinColumns =  @JoinColumn(name = "Subject_id")
    )

    @JsonManagedReference
    private Set<Subject> subjects=new HashSet<>();

    public Student(Long id, String name, String emailId, Set<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.subjects = subjects;
    }

    public Student(String name, String emailId) {
        this.name=name;
        this.emailId=emailId;
    }

    public void addSubject(Subject subject) {
      subjects.add(subject);
    }
    public void addSubject(Set<Subject> subject) {
        this.subjects=subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
