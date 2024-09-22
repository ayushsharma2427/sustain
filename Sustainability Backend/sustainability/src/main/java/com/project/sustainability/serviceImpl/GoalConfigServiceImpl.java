package com.project.sustainability.serviceImpl;

import com.project.sustainability.service.GoalConfigService;
import org.springframework.stereotype.Service;
import com.project.sustainability.model.goalConfig.GoalConfig;
import com.project.sustainability.repository.GoalConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class GoalConfigServiceImpl implements GoalConfigService {

    @Autowired
    private  GoalConfigRepo goalConfigRepo;

    @Override
    public List<GoalConfig> getAllGoalConfigs() {
        return goalConfigRepo.findAll();
    }
}
