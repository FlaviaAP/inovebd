package com.i9.controllers;

import com.i9.models.Task;
import com.i9.services.PhaseService;
import com.i9.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TaskRestController {

    @Resource
    TaskService taskService;

    @Resource
    PhaseService phaseService;

    @RequestMapping(value="project/createTask",method = RequestMethod.POST)
    public ResponseEntity<Integer> postCreateTask(@RequestBody(required = false) Task task, Model model){
        taskService.saveTask(task);
        return new ResponseEntity<>(phaseService.getPhase(task.getPhaseId()).getProjectId(),HttpStatus.OK);
    }
}
