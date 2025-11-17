package com.taskmanagerGo.chatgptGoproject1.repository;

import com.taskmanagerGo.chatgptGoproject1.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    // Find employee by email (returns Optional)
    Optional<Task> findByEmail(String email);

    // Check quickly if an email already exists (useful for create/update checks)
    boolean existsByEmail(String email);
}
