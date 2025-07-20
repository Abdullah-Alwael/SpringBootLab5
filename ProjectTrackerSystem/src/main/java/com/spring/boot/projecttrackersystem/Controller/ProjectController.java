package com.spring.boot.projecttrackersystem.Controller;

import com.spring.boot.projecttrackersystem.Api.ApiResponse;
import com.spring.boot.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
//    Q2 : Create project tracker system , Where you can get all the project ,
//    add , remove , update project.
    ArrayList<Project> projects = new ArrayList<>();
    int idCounter = 0;

//• Create a new project (ID,title , description , status, companyName)
    @PostMapping("/new")
    public ApiResponse createProject(@RequestBody Project project){
        project.setID(Integer.toString(idCounter));

        projects.add(project);
        return new ApiResponse("Project created successfully", "200 OK");
    }
//• Display all project .
//• Update a project
//• Delete a project
//• Change the project status as done or not done
//• Search for a project by given title
//• Display All project for one company by companyName.
}
