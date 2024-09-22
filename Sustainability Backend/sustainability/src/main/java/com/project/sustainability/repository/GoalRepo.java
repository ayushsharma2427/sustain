package com.project.sustainability.repository;

import com.project.sustainability.model.goals.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepo extends MongoRepository<Goal, String> {
//    Goal findBySupplierId(String supplierId);
    Optional<Goal> findBySupplierIdAndYearAndPillarName(String supplierId, String year, String pillarName);
    List<Goal> findBySupplierIdAndYear(String supplierId, String year);
}
