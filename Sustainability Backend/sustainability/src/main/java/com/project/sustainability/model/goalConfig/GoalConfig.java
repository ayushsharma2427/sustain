package com.project.sustainability.model.goalConfig;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "goalConfig")
public class GoalConfig {

    @Id
    private String _id;
    private String pillarName;
    private String year;
    private List<Question> questions;
}
