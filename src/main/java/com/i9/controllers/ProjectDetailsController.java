package com.i9.controllers;

import com.i9.models.Client;
import com.i9.models.Project;
import com.i9.services.ClientService;
import com.i9.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/project")
public class ProjectDetailsController {

    @Resource
    private ProjectService projectService;

    @Resource
    private ClientService clientService;

    @RequestMapping(value = "/details")
    public String detailProjectPage(@RequestParam() int projectId, Model model){
        Project project = projectService.getProject(projectId);
        Client client = clientService.getClient(project.getCNPJCPF());

        model.addAttribute("project", project);
        model.addAttribute("client", client);
        return "projectDetails";
    }
}
