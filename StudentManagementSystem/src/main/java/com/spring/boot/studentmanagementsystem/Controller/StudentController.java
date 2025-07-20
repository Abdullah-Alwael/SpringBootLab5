package com.spring.boot.studentmanagementsystem.Controller;

import com.spring.boot.studentmanagementsystem.Api.ApiResponse;
import com.spring.boot.studentmanagementsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    //    Q1 : Create Spring program with request on the following endpoints :
//    Create a controller called studentController with the following endpoints.
    ArrayList<Student> students = new ArrayList<>();
    int idCounter = 0;

    @PostMapping("/new")
    public ApiResponse createStudent(@RequestBody Student student) {
        idCounter++;
        student.setID(Integer.toString(idCounter));
        students.add(student);
        return new ApiResponse("Student was created successfully!", "200 OK");
    }

    @GetMapping("/list")
    public ArrayList<Student> displayStudents() {
        return students;
    }

    //• Update a student
    @PutMapping("/update/{iD}")
    public ApiResponse updateStudent(@PathVariable String iD, @RequestBody Student student) {
        boolean updated = false;

        for (Student s : students) {
            if (s.getID().equals(iD)) {
                s.setName(student.getName());
                s.setAge(student.getAge());
                s.setDegree(student.getDegree());
                s.setGPA(student.getGPA());
                updated = true;
            }
        }
        if (updated) {
            return new ApiResponse("Student with id: was successfully updated", "200 OK");
        } else {
            return new ApiResponse("Error, the student " + student.getName() + " with id: " + student.getID()
                    + " does not exist", "404 Not found");
        }
    }

    //• Delete a student
    @DeleteMapping("/delete/{iD}")
    public ApiResponse deleteStudent(@PathVariable String iD) {
        boolean deleted = false;

        for (Student s : students) {
            if (s.getID().equals(iD)) {
                students.remove(s);
                deleted = true;
            }
        }
        if (deleted) {
            return new ApiResponse("Student was deleted successfully", "200 OK");
        } else {
            return new ApiResponse("Error student does not exist","404 Not found");
        }
    }
//• Based on GPA, classify students into honors categories.
//• Display a group of students whose GPA is greater than the average
//    GPA.
}
