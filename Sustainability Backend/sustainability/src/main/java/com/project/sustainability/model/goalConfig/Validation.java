package com.project.sustainability.model.goalConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Validation {

    private String validationQuestionId;
    private String type;
    private List<String> relatedIds;
}
