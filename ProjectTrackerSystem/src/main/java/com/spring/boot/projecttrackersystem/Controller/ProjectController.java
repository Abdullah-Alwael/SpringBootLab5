package com.spring.boot.projecttrackersystem.Controller;

import com.spring.boot.projecttrackersystem.Api.ApiResponse;
import com.spring.boot.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

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

    @PutMapping("/update/{iD}")
    public ApiResponse updateProject(@PathVariable String iD, @RequestBody Project project) {
        boolean updated = false;
        project.setID(iD);

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

    @DeleteMapping("/delete/{iD}")
    public ApiResponse deleteProject(@PathVariable String iD) {
        boolean deleted = false;

        for (Project p : projects) {
            if (p.getID().equals(iD)) {
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

    @PutMapping("/set-status/{iD}/{status}")
    public ApiResponse changeStatus(@PathVariable String iD, @PathVariable String status) {
        if (!status.equals("Done") && !status.equals("Not Done")) {
            return new ApiResponse("Error, status must be either Done on Not Done (case sensitive)"
                    , "400 Bad Request");
        }

        boolean statusChanged = false;

        for (Project p : projects) {
            if (p.getID().equals(iD)) {
                p.setStatus(status);
                statusChanged = true;
                break;
            }
        }
        if (statusChanged) {
            return new ApiResponse("Project status changed successfully", "200 OK");
        } else {
            return new ApiResponse("Error project does not exist", "404 Not found");
        }

    }

    @GetMapping("/filter/by-title/{title}")
    public ArrayList<Project> searchProjectByTitle(@PathVariable String title) {
        ArrayList<Project> foundByTitle = new ArrayList<>();

        for (Project p : projects) {
            if (p.getTitle().contains(title)) {
                foundByTitle.add(p);
            }
        }

        return foundByTitle;
    }

@GetMapping("/filter/by-company-name/{companyName}")
public ArrayList<Project> searchProjectByCompanyName(@PathVariable String companyName) {
    ArrayList<Project> foundByCompanyName = new ArrayList<>();

    for (Project p : projects) {
        if (p.getCompanyName().contains(companyName)) {
            foundByCompanyName.add(p);
        }
    }

    return foundByCompanyName;
}
}
