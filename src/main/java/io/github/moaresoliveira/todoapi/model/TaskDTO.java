package io.github.moaresoliveira.todoapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private String name;
    private String description;

    public TaskDTO(Task task){
        this.name = task.getName();
        this.description = task.getDescription();
    }

    public Task toModel(){
        return new Task(this.name, this.description);
    }

}
