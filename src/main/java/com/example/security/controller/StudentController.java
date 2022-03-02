package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController //Indetify a class that handle the HTTP requests.
@RequestMapping("api/v1/students") // the annotation is used to map web requests to Spring Controller methods.
public class StudentController {
    private static final List<Student> STUDENTS = Arrays.asList(
        new Student(1, "Isabel"),
        new Student(2,"Flavia"),
        new Student(3,"Marcelo")
    );

    @GetMapping(path = "{studentId}")//annotated methods in the @Controller annotated classes handle the HTTP GET requests matched with given URI expression.
    public Student getStudent(@PathVariable("studentId") Integer studentId){
     return STUDENTS.stream()
             .filter(student -> studentId.equals(student.getStudentId())).
             findFirst().
             orElseThrow(() -> new IllegalStateException("Student" + studentId + "does not exists"));
    }


}
