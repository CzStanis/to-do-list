package com.Wproject.to_do_list.service;

import com.Wproject.to_do_list.entity.Task;
import com.Wproject.to_do_list.entity.TaskCategory;
import com.Wproject.to_do_list.repository.TaskCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Wproject.to_do_list.repository.TaskRepository;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskCategoryService taskCategoryService;
    private TaskRepository taskRepository;
    private TaskCategoryRepository categoryRepository;
    public TaskService(TaskRepository taskRepository, TaskCategoryService taskCategoryService,
                       TaskCategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.taskCategoryService = taskCategoryService;
        this.categoryRepository = categoryRepository;
    }

    public void createNewTask(Task task) {
        if (task.getCategory()==null) {
            throw new ResourceAccessException("you have to declare category");
        }
        Optional<Task> taskOptional = taskRepository
                .findOptionalTaskByName(task.getName());
        System.out.println(task.toString());
        System.out.println(taskOptional.toString());

        if (taskOptional.isPresent() && taskOptional.get().getCategory().getName().equals(task.getCategory().getName())) {
            throw new IllegalStateException("task exists in this category");
        }

        TaskCategory category = taskCategoryService.findCategoryByNameOrNull(task.getCategory().getName());
        if (category != null) {
            task.setCategory(category);
        } else {
            TaskCategory newCategory = categoryRepository.save(task.getCategory());
            task.setCategory(newCategory);
        }
        taskRepository.save(task);
    }

    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Task not found"));
    }
    @Transactional
    public void completeOrNotTask(String name, TaskCategory category) {
        List<Task> tasks = taskRepository.findTaskByName(name);
        Task task = tasks.stream()
                .filter(t -> t.getCategory().getName().equals(category.getName()))
                .findFirst()
                .orElseThrow(() -> new ResourceAccessException("Task doesn't exist in the specified category"));

        if(!task.isCompleted()) {
            task.setCompleted(true);
        } else {
            task.setCompleted(false);
        }
        taskRepository.save(task);

//        Task task = taskRepository.findOptionalTaskByName(name).
//                orElseThrow(() -> new ResourceAccessException("task doesn't exist"));
//        if (task.getCategory().getName().equals(category.getName())) {
//            task.setCompleted(true);
//            taskRepository.save(task);
//        } else {
//            throw new IllegalArgumentException("Task exists but is in a different category");
//        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
