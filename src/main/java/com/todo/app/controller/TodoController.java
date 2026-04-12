package com.todo.app.controller;

import com.todo.app.dto.TodoRequestDTO;
import com.todo.app.dto.TodoResponseDTO;
import com.todo.app.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @GetMapping
    public List<TodoResponseDTO> getAll(
            @RequestParam(required = false) Boolean completed) {
        if (completed != null) return service.findByStatus(completed);
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TodoResponseDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<TodoResponseDTO> create(
            @Valid @RequestBody TodoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public TodoResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequestDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}