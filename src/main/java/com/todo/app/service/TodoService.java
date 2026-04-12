package com.todo.app.service;

import com.todo.app.dto.TodoRequestDTO;
import com.todo.app.dto.TodoResponseDTO;
import java.util.List;

public interface TodoService {
    List<TodoResponseDTO> findAll();
    List<TodoResponseDTO> findByStatus(boolean completed);
    TodoResponseDTO findById(Long id);
    TodoResponseDTO create(TodoRequestDTO request);
    TodoResponseDTO update(Long id, TodoRequestDTO request);
    void delete(Long id);
}