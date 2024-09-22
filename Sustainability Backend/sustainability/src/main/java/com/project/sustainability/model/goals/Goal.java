package com.project.sustainability.model.goals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "supplierGoal")
public class Goal{
    @Id
    private String _id;
    private String supplierId;
    private String year;
    private String pillarName;
    private ArrayList<GoalAnswers> goalAnswers;
    private String updatedDt;
    private String updatedUser;
}
