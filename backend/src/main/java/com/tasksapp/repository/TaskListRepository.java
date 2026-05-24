package com.tasksapp.repository;

import com.tasksapp.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for TaskList entity
 * Provides database operations for task lists
 */
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByUserId(Long userId);
    Optional<TaskList> findByIdAndUserId(Long id, Long userId);
}
