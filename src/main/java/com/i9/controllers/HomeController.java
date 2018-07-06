package com.i9.controllers;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeDao;
import com.i9.models.Employee;
import com.i9.models.Project;
import com.i9.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HomeController {
    @Resource
    private ProjectService projectService;

    @RequestMapping("/")
    public String redirectToHomepage(){
        return "redirect:/home";
    }

    @RequestMapping(value = "/home")
    public String homePage(Model model){
        List<Project> projectList = projectService.getProjects();
        model.addAttribute("projects", projectList);
        return "home";
    }
}
