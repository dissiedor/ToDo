package com.example.TodoFedorin.Repository;

import com.example.TodoFedorin.Entity.TasksEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TasksEntity, Long> {
    Page<TasksEntity> findAllByStatus(boolean status, Pageable pageable);

    @Query("SELECT COUNT(*) FROM TasksEntity t WHERE t.status = ?1")
    long countByStatus(boolean status);
}
