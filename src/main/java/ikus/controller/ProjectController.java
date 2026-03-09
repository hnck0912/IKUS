package ikus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ikus.repository.ProjectRepository projectRepository;

    @PostMapping
    public ikus.entity.Project createProject(@RequestBody ikus.entity.Project project) {
        return projectRepository.save(project);
    }
}
