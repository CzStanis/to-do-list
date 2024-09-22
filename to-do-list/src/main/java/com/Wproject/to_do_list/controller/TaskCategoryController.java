package com.Wproject.to_do_list.controller;

import com.Wproject.to_do_list.entity.TaskCategory;
import com.Wproject.to_do_list.service.TaskCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/categories")
public class TaskCategoryController {
    private final TaskCategoryService categoryService;
    public TaskCategoryController(TaskCategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("get/all")
    public List<TaskCategory> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/add")
    public void createCategory(@RequestBody TaskCategory category) {
        categoryService.add(category);
    }

    @GetMapping("/{name}")
    public TaskCategory getCategoryByName(@PathVariable String name) {
        return categoryService.findCategoryByNameOrThrow(name);
    }

    @DeleteMapping("/{name}")
    public void deleteCategory(@PathVariable String name) {
        categoryService.deleteByName(name);
    }
}
