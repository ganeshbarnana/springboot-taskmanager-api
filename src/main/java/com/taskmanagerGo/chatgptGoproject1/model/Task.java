package com.taskmanagerGo.chatgptGoproject1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee JPA entity — persistence model.
 * This is NOT the API contract. Do not return entities directly from controllers.
 */
@Entity
@Table(name = "employees",
        indexes = {
                @Index(name = "idx_emp_email", columnList = "email")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // persisted and required
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * Mark email unique to avoid duplicate contacts.
     * Optional behavior: make unique=true to let DB enforce uniqueness.
     * For small projects, an index + unique constraint is common.
     */
    @Column(name = "email", nullable = true, length = 100, unique = true)
    private String email;

    /**
     * Internal/persistent field that we deliberately do NOT expose in DTO
     * (salary is sensitive / internal business data).
     */
    @Column(name = "salary")
    private Double salary;

    @Column(name = "department", length = 100)
    private String department;
}
