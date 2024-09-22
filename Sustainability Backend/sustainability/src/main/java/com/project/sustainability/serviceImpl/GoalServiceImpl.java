package com.project.sustainability.serviceImpl;

import com.project.sustainability.controller.GoalController;
import com.project.sustainability.exception.SupplierNotFoundException;
import com.project.sustainability.model.goals.Goal;
import com.project.sustainability.repository.GoalRepo;
import com.project.sustainability.service.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class GoalServiceImpl implements GoalService {
    @Autowired
    GoalRepo goalRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    Logger logger= LoggerFactory.getLogger(GoalController.class);

    @Override
    public Goal addOrUpdateGoalData(Goal goal) {
        log.info("Entering addOrUpdateGoalData with supplierModel: {}", goal);

        // Check if a record exists for the combination of supplierId, year, and pillarName
        Optional<Goal> existingGoal = goalRepo.findBySupplierIdAndYearAndPillarName(
                goal.getSupplierId(),
                goal.getYear(),
                goal.getPillarName());

        Goal savedModel;

        if (existingGoal.isPresent()) {
            // Update the existing record
            Goal goalToUpdate = existingGoal.get();
            goalToUpdate.setGoalAnswers(goal.getGoalAnswers());
            goalToUpdate.setUpdatedDt(goal.getUpdatedDt());
            goalToUpdate.setUpdatedUser(goal.getUpdatedUser());
            savedModel = goalRepo.save(goalToUpdate);
            log.info("Exiting addOrUpdateGoalData with updated supplierModel: {}", savedModel);
        } else {
            // Add a new record
            savedModel = goalRepo.save(goal);
            log.info("Exiting addOrUpdateGoalData with newly added supplierModel: {}", savedModel);
        }

        return savedModel;
    }


//    @Override
//    public Goal getSupplierById(String supplierId) {
//
//        Goal supplier = goalRepo.findBySupplierId(supplierId);
//        if (supplier==null) {
//            log.error("SupplierModel with ID: {} not found", supplierId);
//            throw new SupplierNotFoundException(supplierId);
//        }
//        log.info("SupplierModel with ID: {} found", supplierId);
//        return supplier;
//    }


    @Override
    public void deleteGoalsByFields(String supplierId, String pillarName, String year) {
        logger.info("Inside deleteGoalsByFields method");

        // Construct the query with additional 'year' criteria
        Query query = new Query();
        query.addCriteria(Criteria.where("supplierId").is(supplierId)
                .and("pillarName").is(pillarName)
                .and("year").is(year));

        try {
            long deletedCount = mongoTemplate.remove(query, Goal.class).getDeletedCount();
            if (deletedCount == 0) {
                logger.warn("No documents found matching the criteria: supplierId={}, pillarName={}, year={}", supplierId, pillarName, year);
                throw new SupplierNotFoundException("No documents found matching the criteria");
            } else {
                logger.info("Successfully deleted {} documents matching the criteria: supplierId={}, pillarName={}, year={}", deletedCount, supplierId, pillarName, year);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting documents: supplierId={}, pillarName={}, year={}", supplierId, pillarName, year, e);
            throw new RuntimeException("Error occurred while deleting documents: " + e.getMessage());
        }
    }

    @Override
    public List<Goal> getSuppliersBySupplierIdAndYear(String supplierId, String year) {
        log.info("Fetching SupplierModel with ID: {} and Year: {}", supplierId, year);
        List<Goal> supplierModels = goalRepo.findBySupplierIdAndYear(supplierId, year);
        if (supplierModels.isEmpty()) {
            log.error("No SupplierModel found with ID: {} and Year: {}", supplierId, year);
            throw new SupplierNotFoundException("Supplier ID: " + supplierId + " and Year: " + year);
        }
        log.info("Found {} records for SupplierModel with ID: {} and Year: {}", supplierModels.size(), supplierId, year);
        return supplierModels;
    }
}
