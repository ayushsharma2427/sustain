package com.project.sustainability.repository;

import com.project.sustainability.model.goalConfig.GoalConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalConfigRepo extends MongoRepository<GoalConfig, String>{
}
