package com.tasktracker.task_tracker;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask){
        Optional<Task> existing= taskRepository.findById(id);
        if(existing.isPresent()){
            Task task=existing.get();
            task.setDescription(updatedTask.getDescription());
            task.setDone(updatedTask.isDone());
            return Optional.of(task);
        }
        return Optional.empty();
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
