package com.project.sustainability.controller;


import com.project.sustainability.model.goalConfig.GoalConfig;
import com.project.sustainability.service.GoalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class GoalConfigController {
    @Autowired
    private  GoalConfigService goalConfigService;


    @GetMapping("/goalsData")
    public List<GoalConfig> getAllGoalConfigs() {
        return goalConfigService.getAllGoalConfigs();
    }
}
