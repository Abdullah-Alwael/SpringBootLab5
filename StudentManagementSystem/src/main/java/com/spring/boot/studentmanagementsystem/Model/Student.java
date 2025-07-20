package com.spring.boot.studentmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String iD, name;
    private int age;
    private String degree;
    private double gPA;

}
