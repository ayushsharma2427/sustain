package com.project.sustainability.model.goals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GoalAnswers {
    private String questionId;
    private ArrayList<Response> response;
}

