package com.taskmanagerGo.chatgptGoproject1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {




        /**
         * id is server-generated. For create requests clients should not send id.
         * Marked @Null to make it explicit in create operations (optional but good for interviews).
         * If you prefer accepting id in updates, remove @Null or use separate DTOs for create/update.
         */
       @Null(message = "id must be null for create requests")
        private Long id;

        /**
         * firstName: required, not blank, reasonable length limit.
         */
        @NotBlank(message = "firstName is required")
        @Size(max = 50, message = "firstName must be at most 50 characters")
        private String firstName;

        /**
         * lastName: required, not blank.
         */
        @NotBlank(message = "lastName is required")
        @Size(max = 50, message = "lastName must be at most 50 characters")
        private String lastName;

        /**
         * email: optional for now, but if provided must be a valid email.
         * For many real projects email is required — we keep it optional so your API is flexible.
         */
        @Email(message = "email must be valid")
        @Size(max = 100, message = "email must be at most 100 characters")
        private String email;


 }

