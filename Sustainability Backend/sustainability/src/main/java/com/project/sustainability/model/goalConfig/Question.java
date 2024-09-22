package com.project.sustainability.model.goalConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private String questionId;
    private String text;
    private List<InputField> inputFields;
    private List<Validation> validations;
}
