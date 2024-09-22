package com.Wproject.to_do_list.repository;

import com.Wproject.to_do_list.entity.TaskCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    @Query("SELECT c FROM TaskCategory c WHERE c.name = ?1")
    Optional<TaskCategory> findCategoryByName(String name);
    @Query("SELECT c FROM TaskCategory c WHERE c.name = ?1")
    TaskCategory findByName(String name);
    @Modifying
    @Transactional
    @Query("DELETE FROM TaskCategory c WHERE c.name = ?1")
    void deleteByName(String name);
}