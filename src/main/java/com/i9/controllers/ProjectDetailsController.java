package com.i9.controllers;


import com.i9.models.*;
import com.i9.services.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectDetailsController {

    @Resource
    private EmployeeService employeeService;

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
        model.addAttribute("timeFormatter", DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        return "projectDetails";
    }

    @RequestMapping(value="/createTask",method = RequestMethod.GET)
    public String getCreateTask(Integer phaseId, Model model){
        model.addAttribute("phaseId",phaseId);
        model.addAttribute("employees",employeeService.getEmployees());
        return "createTask";
    }

    @RequestMapping(value="/createTask",method = RequestMethod.POST)
    public String postCreateTask(@RequestBody(required = false) Task task, Model model){
        taskService.saveTask(task);
        return "redirect:/details";
    }
}
