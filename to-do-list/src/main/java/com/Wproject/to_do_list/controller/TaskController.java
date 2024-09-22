package com.Wproject.to_do_list.controller;

import com.Wproject.to_do_list.entity.Task;
import com.Wproject.to_do_list.entity.TaskCategory;
import com.Wproject.to_do_list.service.TaskCategoryService;
import com.Wproject.to_do_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/get/all")
    public List<Task> getAllTasks() {
        return taskService.findAllTask();
    }
    @PostMapping("/add")
    public void createTask(@RequestBody Task task) {
        taskService.createNewTask(task);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    @PutMapping("/{name}")
    public void completeTask(@PathVariable String name, @RequestBody TaskCategory category) {
        taskService.completeOrNotTask(name, category);
    }
}
