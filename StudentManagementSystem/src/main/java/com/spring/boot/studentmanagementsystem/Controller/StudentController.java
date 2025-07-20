package com.spring.boot.studentmanagementsystem.Controller;

import com.spring.boot.studentmanagementsystem.Api.ApiResponse;
import com.spring.boot.studentmanagementsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

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

    @PutMapping("/update/{iD}")
    public ApiResponse updateStudent(@PathVariable String iD, @RequestBody Student student) {
        boolean updated = false;

        student.setID(iD);

        for (Student s : students) {
            if (s.getID().equals(iD)) {
                students.set(students.indexOf(s), student);
                updated = true;
                break;
            }
        }
        if (updated) {
            return new ApiResponse("Student with id: was successfully updated", "200 OK");
        } else {
            return new ApiResponse("Error, the student " + student.getName() + " with id: " + student.getID()
                    + " does not exist", "404 Not found");
        }
    }

    @DeleteMapping("/delete/{iD}")
    public ApiResponse deleteStudent(@PathVariable String iD) {
        boolean deleted = false;

        for (Student s : students) {
            if (s.getID().equals(iD)) {
                students.remove(s);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            return new ApiResponse("Student was deleted successfully", "200 OK");
        } else {
            return new ApiResponse("Error student does not exist", "404 Not found");
        }
    }

    @GetMapping("check-honor/{iD}")
    public ApiResponse checkHonorStudent(@PathVariable String iD) {
        for (Student s:students){
            if (s.getID().equals(iD)){
                if (s.getGPA() >= 4.75){
                    return new ApiResponse("Student "+s.getName()+" with GPA: "
                            +s.getGPA()+" is an honor student on the first deans' list", "200 OK");
                } else if (s.getGPA() >= 4.25){
                    return new ApiResponse("Student "+s.getName()+" with GPA: "
                            +s.getGPA()+" is an honor student on the second deans' list", "200 OK");
                } else {
                    return new ApiResponse("Student "+s.getName()+" with GPA: "
                            +s.getGPA()+" is not an honor student", "200 OK");
                }
            }
        }
        return new ApiResponse("Error, student does not exist", "404 Not found");
    }

    @GetMapping("/above-average-list")
    public ArrayList<Student> aboveAverageStudents() {
        ArrayList<Student> aboveAverage = new ArrayList<>();
        double sum = 0;
        for (Student s : students) {
            sum += s.getGPA();
        }

        double average = sum / students.size();

        for (Student s : students) {
            if (s.getGPA() > average) {
                aboveAverage.add(s);
            }
        }

        return aboveAverage;
    }
}
