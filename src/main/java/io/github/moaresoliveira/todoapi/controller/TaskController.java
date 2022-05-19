package io.github.moaresoliveira.todoapi.controller;

import io.github.moaresoliveira.todoapi.model.Task;
import io.github.moaresoliveira.todoapi.model.TaskDTO;
import io.github.moaresoliveira.todoapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Optional<Task> optionalTask = repository.findById(id);
        if(optionalTask.isPresent()){
            return ResponseEntity.ok(optionalTask.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO){
        Task task = taskDTO.toModel();
        repository.save(task);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Long id){
        Task task = taskDTO.toModel();
        task.setCreationDate(LocalDate.now());
        task.setId(id);
        repository.save(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
