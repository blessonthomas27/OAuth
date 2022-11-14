package com.example.ResourseOwner.repository;

import com.example.ResourseOwner.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Optional<Subject> findBysubjectname(String subjectname);


}
