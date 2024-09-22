package com.project.sustainability.service;
import com.project.sustainability.model.goals.Goal;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GoalService {

    public Goal addOrUpdateGoalData(Goal goal);
//    public Goal getSupplierById(String supplierId);
    public void deleteGoalsByFields(String supplierId, String pillarName, String year);
    List<Goal> getSuppliersBySupplierIdAndYear(String supplierId, String year);

}
