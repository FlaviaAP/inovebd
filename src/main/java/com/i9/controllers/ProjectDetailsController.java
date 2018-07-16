package com.i9.controllers;


import com.i9.models.Client;
import com.i9.models.Phase;
import com.i9.models.Project;
import com.i9.services.ClientService;
import com.i9.services.PhaseService;
import com.i9.services.ProjectService;
import com.i9.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectDetailsController {

    @Resource
    private ProjectService projectService;

    @Resource
    private ClientService clientService;

    @Resource
    private PhaseService phaseService;

    @Resource
    private TaskService taskService;

    @RequestMapping(value = "/details")
    public String detailProjectPage(@RequestParam() int projectId, Model model){
        Project project = projectService.getProject(projectId);
        Client client = clientService.getClient(project.getCNPJCPF());
        List<Phase> phases = phaseService.getPhaseByProject(projectId);

        for(Phase phase : phases) {
            phase.setTasks(taskService.getTasksByPhase(phase.getId()));
            phase.calculateEstimation();
        }

        model.addAttribute("project", project);
        model.addAttribute("client", client);
        model.addAttribute("phases", phases);
        return "projectDetails";
    }
}
