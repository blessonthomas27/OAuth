package com.example.ResourseOwner.service;

import com.example.ResourseOwner.entity.Student;
import com.example.ResourseOwner.entity.Subject;
import com.example.ResourseOwner.repository.StudentRepository;
import com.example.ResourseOwner.repository.SubjectRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubjectService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject){
        try {
        return subjectRepository.save(subject);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public boolean deleteSubject(String id){
        try {
            long _id=Long.parseLong(id);
            subjectRepository.deleteById(_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Subject> recommendedSubjects(String emailId){
        try{
            List<Subject> subjects=subjectRepository.findAll();
            Student student= studentRepository.findByemailId(emailId);
            Set<Subject> student_subject= student.getSubjects();
            subjects.removeAll(student_subject);
            return subjects;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
