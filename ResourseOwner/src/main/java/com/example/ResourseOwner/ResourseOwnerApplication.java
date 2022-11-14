package com.example.ResourseOwner;

import com.example.ResourseOwner.entity.Student;
import com.example.ResourseOwner.entity.Subject;
import com.example.ResourseOwner.repository.StudentRepository;
import com.example.ResourseOwner.repository.SubjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ResourseOwnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourseOwnerApplication.class, args);
//		ConfigurableApplicationContext configurableApplicationContext= SpringApplication.run(ResourseOwnerApplication.class, args);
//		StudentRepository studentRepository=configurableApplicationContext.getBean(StudentRepository.class);
//		SubjectRepository subjectRepository=configurableApplicationContext.getBean(SubjectRepository.class);
//
//
//
//		Student s1 =new Student("Blesson thomas","blessonthomas27@gmail.com");
//		Student s2 =new Student("Ajay sharma","ajaysharma@gmail.com");
//		Student s3 =new Student("Suresh raina","sureshraina@gmail.com");
//		Student s4 =new Student("Darnish ","darnish@gmail.com");
//		Student s5 =new Student("hariprabu ","hariprabu@gmail.com");
//		Student s6 =new Student("vipin ","vipin@gmail.com");
//		Student s7 =new Student("tharun ","tharun@gmail.com");
//		Student s8 =new Student("gowtham","gowtham@gmail.com");
//
//
//		Subject maths =new Subject("Maths","10","3","100");
//		Subject science=new Subject("Science","10","4","89");
//		Subject social=new Subject("Social","10","5","67");
//		Subject english=new Subject("English","10","3","78");
//		Subject tamil=new Subject("Tamil","10","3","120");
//		Subject french=new Subject("French","10","5","140");
//
//
//		Set<Subject> col1= Set.of(maths,science,english,social,tamil,french);
//		Set<Subject> col2= Set.of(maths,science,english,social,tamil,french);
//		Set<Subject> col3= Set.of(maths,science,tamil,french);
//		Set<Subject> col4= Set.of(maths,science,english,social);
//		Set<Subject> col5= Set.of(science,social,tamil,french);
//		Set<Subject> col6= Set.of(maths,tamil,french);
//
//
//		s1.addSubject(col1);
//		s2.addSubject(col2);
//		s3.addSubject(col3);
//		s4.addSubject(col4);
//		s5.addSubject(col5);
//		s6.addSubject(col3);
//		s7.addSubject(col2);
//		s8.addSubject(col6);
//
//		Set<Subject> sss= Set.of(maths,science,social,english,tamil,french);
//		Set<Student> ss= Set.of(s1,s2,s3,s4,s5,s6,s7,s8);
//		subjectRepository.saveAll(sss);
//		studentRepository.saveAll(ss);

	}

}
