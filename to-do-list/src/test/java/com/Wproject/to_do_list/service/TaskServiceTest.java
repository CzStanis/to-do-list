package com.Wproject.to_do_list.service;

import com.Wproject.to_do_list.entity.Task;
import com.Wproject.to_do_list.entity.TaskCategory;
import com.Wproject.to_do_list.repository.TaskCategoryRepository;
import com.Wproject.to_do_list.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskCategoryService taskCategoryService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskCategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void should_successfully_save_task() {
        //Given
        //Mock the calls
        //When
        //Then
    }
    @Test
    void shouldSaveTaskWithExistingCategory() {
        // given
        TaskCategory category = new TaskCategory();
        category.setName("Work");

        Task task = new Task();
        task.setName("New Task");
        task.setCategory(category);

        //Mock the calls
        when(taskRepository.findOptionalTaskByName("New Task")).thenReturn(Optional.empty());
        when(taskCategoryService.findCategoryByNameOrNull("Work")).thenReturn(category);

        // when
        taskService.createNewTask(task);

        // then
        verify(taskRepository).save(task);
        verify(categoryRepository, never()).save(any(TaskCategory.class));
    }
}