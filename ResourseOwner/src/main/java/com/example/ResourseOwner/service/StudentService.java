package com.example.ResourseOwner.service;

import com.example.ResourseOwner.entity.Student;
import com.example.ResourseOwner.entity.Subject;
import com.example.ResourseOwner.repository.StudentRepository;
import com.example.ResourseOwner.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    public Student createStudent(Student student){
        try{
            return studentRepository.save(student);
        }catch (Exception e){
            return null;
        }
    }

    public  Student addSubject(Long subId,String emailId){
        try{
            Subject subject=subjectRepository.findById(subId).get();
            Student student=studentRepository.findByemailId(emailId);
            Set<Subject> subjects=student.getSubjects();
            if(subjects.contains(subject)){
                return null;
            }
            subjects.add(subject);
            return studentRepository.save(student);
        }catch (Exception e){
            return null;
        }
    }

    public Student removeSubject(Long subId,String emailId){
        try{
            Subject subject=subjectRepository.findById(subId).get();
          Student student= studentRepository.findByemailId(emailId);
            Set<Subject> subjects=student.getSubjects();
            if(subjects.contains(subject)){
                subjects.remove(subject);
                return studentRepository.save(student);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public Student getDetails(String emailId) {
        try {
            return studentRepository.findByemailId(emailId);
        }catch (Exception e){
            return null;
        }
    }
}
