package com.project.sustainability.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.sustainability.exception.SupplierIdNotFoundException;
import com.project.sustainability.exception.SupplierNotFoundException;
import com.project.sustainability.model.goals.Goal;
import com.project.sustainability.repository.GoalRepo;
import com.project.sustainability.serviceImpl.GoalServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api")

public class GoalController {

   @Autowired
   GoalServiceImpl goalService;

   @Autowired
   GoalRepo goalRepo;

    private static final Logger logger = LoggerFactory.getLogger(GoalController.class);


    @PostMapping("/addOrUpdateGoalData")
    public ResponseEntity<String> addOrUpdateGoalData(@RequestBody Goal goal) throws JsonProcessingException {
        log.info("Received request to add or update goal data: {}", goal);
        Goal result = goalService.addOrUpdateGoalData(goal);
        log.info("Goal data processed successfully for supplierId: {}", result.getSupplierId());
        return ResponseEntity.ok("Goal data added or updated successfully.");
    }

    @GetMapping("/{supplierId}/{year}")
    public ResponseEntity<List<Goal>> getSupplierModelsBySupplierIdAndYear(
            @PathVariable String supplierId, @PathVariable String year) {
        log.info("Request received to get SupplierModel with ID: {} and Year: {}", supplierId, year);
        List<Goal> supplierModels = goalService.getSuppliersBySupplierIdAndYear(supplierId, year);
        return new ResponseEntity<>(supplierModels, HttpStatus.OK);
    }

    @DeleteMapping("/resetGoals")
    public ResponseEntity<String> deleteGoals(
            @RequestParam(value = "supplierId", required = false) String supplierId,
            @RequestParam(value = "pillarName", required = false) String pillarName,
            @RequestParam(value = "year", required = false) String year) {

        logger.info("deleteGoals controller starts");

        // Check if any of the required parameters are missing
        if (supplierId == null || pillarName == null || year == null) {
            logger.warn("Missing required parameters: supplierId, pillarName, or year");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Missing required parameters: 'supplierId', 'pillarName', or 'year'");
        }

        try {
            goalService.deleteGoalsByFields(supplierId, pillarName, year);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Error occurred while deleting goals", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Internal server error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
