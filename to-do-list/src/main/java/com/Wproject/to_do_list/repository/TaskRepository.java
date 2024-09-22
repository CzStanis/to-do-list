package com.Wproject.to_do_list.repository;

import com.Wproject.to_do_list.entity.Task;
import com.Wproject.to_do_list.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.name = ?1")
    List<Task> findTaskByName(String name);
    @Query("SELECT t FROM Task t WHERE t.name = ?1")
    Optional<Task> findOptionalTaskByName(String name);
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
    List<Task> findAll();
    Optional<Task> findById(Long id);


}
