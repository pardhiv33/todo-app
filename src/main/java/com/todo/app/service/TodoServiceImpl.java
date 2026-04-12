package com.todo.app.service;

import com.todo.app.dto.TodoRequestDTO;
import com.todo.app.dto.TodoResponseDTO;
import com.todo.app.exception.TodoNotFoundException;
import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Override
    public List<TodoResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse).toList();
    }

    @Override
    public List<TodoResponseDTO> findByStatus(boolean completed) {
        return repository.findByCompleted(completed).stream()
                .map(this::toResponse).toList();
    }

    @Override
    public TodoResponseDTO findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    @Override
    public TodoResponseDTO create(TodoRequestDTO req) {
        Todo todo = new Todo();
        todo.setTitle(req.getTitle());
        todo.setDescription(req.getDescription());
        todo.setCompleted(req.isCompleted());
        return toResponse(repository.save(todo));
    }

    @Override
    public TodoResponseDTO update(Long id, TodoRequestDTO req) {
        Todo todo = getOrThrow(id);
        todo.setTitle(req.getTitle());
        todo.setDescription(req.getDescription());
        todo.setCompleted(req.isCompleted());
        return toResponse(repository.save(todo));
    }

    @Override
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    private Todo getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    private TodoResponseDTO toResponse(Todo todo) {
        TodoResponseDTO dto = new TodoResponseDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setCompleted(todo.isCompleted());
        dto.setCreatedAt(todo.getCreatedAt());
        dto.setUpdatedAt(todo.getUpdatedAt());
        return dto;
    }
}