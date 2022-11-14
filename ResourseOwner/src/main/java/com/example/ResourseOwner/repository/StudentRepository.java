package com.example.ResourseOwner.repository;

import com.example.ResourseOwner.entity.Student;
import com.example.ResourseOwner.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByemailId(String emailId);
}
