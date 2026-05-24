package com.tasksapp.repository;

import com.tasksapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Task entity
 * Provides database operations for tasks
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskListId(Long taskListId);
    List<Task> findByTaskListIdAndParentTaskIsNull(Long taskListId);
    List<Task> findByParentTaskId(Long parentTaskId);
    Optional<Task> findByIdAndTaskListId(Long taskId, Long taskListId);
}
