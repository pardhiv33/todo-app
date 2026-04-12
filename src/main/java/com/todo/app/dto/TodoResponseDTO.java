package com.todo.app.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TodoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}