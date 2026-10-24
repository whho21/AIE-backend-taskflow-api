package sg.edu.ntu.taskflow_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    

}
