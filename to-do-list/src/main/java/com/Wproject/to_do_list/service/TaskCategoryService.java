package com.Wproject.to_do_list.service;

import com.Wproject.to_do_list.entity.TaskCategory;
import com.Wproject.to_do_list.repository.TaskCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskCategoryService {
    private TaskCategoryRepository categoryRepository;
    public TaskCategoryService(TaskCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<TaskCategory> findAll() {return categoryRepository.findAll();}
    public void add(TaskCategory category) {
        Optional<TaskCategory> categoryOptional = categoryRepository.
                findCategoryByName(category.getName());
        if(categoryOptional.isPresent()) {
            throw new IllegalStateException("category exists");
        }
        categoryRepository.save(category);
    }
    public TaskCategory findCategoryByNameOrThrow(String name) {
        TaskCategory category = categoryRepository.findByName(name);
        if(category==null) {
            throw new ResourceAccessException("category "+name+" doesn't exist");
        }
        return category;
    }
    public TaskCategory findCategoryByNameOrNull(String name) {
        return categoryRepository.findByName(name);
    }

    public void deleteByName(String name) {
        categoryRepository.deleteByName(name);
    }
}
