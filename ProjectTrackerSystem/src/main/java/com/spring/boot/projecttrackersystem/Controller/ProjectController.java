package com.spring.boot.projecttrackersystem.Controller;

import com.spring.boot.projecttrackersystem.Api.ApiResponse;
import com.spring.boot.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    //    Q2 : Create project tracker system , Where you can get all the project ,
//    add , remove , update project.
    ArrayList<Project> projects = new ArrayList<>();
    int idCounter = 0;

    @PostMapping("/new")
    public ApiResponse createProject(@RequestBody Project project) {
        idCounter++;
        project.setID(Integer.toString(idCounter));
        projects.add(project);
        return new ApiResponse("Project created successfully", "200 OK");
    }

    @GetMapping("/list")
    public ArrayList<Project> displayProjects() {
        return projects;
    }

    //• Update a project
    @PutMapping("/update/{iD}")
    public ApiResponse updateProject(@PathVariable String iD, @RequestBody Project project) {
        boolean updated = false;
        for (Project p : projects) {
            if (p.getID().equals(iD)) {
                projects.set(projects.indexOf(p), project);
                updated = true;
                break;
            }
        }
        if (updated) {
            return new ApiResponse("Project updated successfully", "200 OK");
        } else {
            return new ApiResponse("Error project does not exist", "404 Not found");
        }
    }

//• Delete a project
    @DeleteMapping("/delete/{iD}")
    public ApiResponse deleteProject(@PathVariable String iD){
        boolean deleted = false;

        for (Project p :projects){
            if (p.getID().equals(iD)){
                projects.remove(p);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            return new ApiResponse("Project deleted successfully", "200 OK");
        } else {
            return new ApiResponse("Error project does not exist", "404 Not found");
        }
    }

//• Change the project status as done or not done

//• Search for a project by given title
//• Display All project for one company by companyName.
}
