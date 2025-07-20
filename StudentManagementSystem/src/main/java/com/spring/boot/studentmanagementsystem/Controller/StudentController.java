package com.spring.boot.studentmanagementsystem.Controller;

import com.spring.boot.studentmanagementsystem.Api.ApiResponse;
import com.spring.boot.studentmanagementsystem.Model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
//    Q1 : Create Spring program with request on the following endpoints :
//    Create a controller called studentController with the following endpoints.
    ArrayList<Student> students = new ArrayList<>();
    int idCounter = 0;
//• Create a new student (ID, ) -- post
    @PostMapping("/new")
    public ApiResponse createStudent(@RequestBody Student student){
        students.add(student);
        idCounter++;
        return new ApiResponse("Student was created successfully!", "200 OK");
    }

//• Display all students.
//• Update a student
//• Delete a student
//• Based on GPA, classify students into honors categories.
//• Display a group of students whose GPA is greater than the average
//    GPA.
}
