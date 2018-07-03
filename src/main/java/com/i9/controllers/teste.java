package com.i9.controllers;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeDao;
import com.i9.models.Employee;
import com.i9.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class teste {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String redirectToHomepage(){
        return "redirect:/employeeList";
    }

    @RequestMapping(value = "/employeeList")
    public String listEmployees(Model model){
        List<Employee> employeeList = employeeService.getEmployees();
        model.addAttribute("empregados", employeeList);
        return "index";
    }
}
