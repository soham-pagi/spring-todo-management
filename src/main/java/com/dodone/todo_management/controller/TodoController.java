package com.dodone.todo_management.controller;

import com.dodone.todo_management.dto.TodoDto;
import com.dodone.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto obtainedTodo = todoService.getTodo(todoId);

        return new ResponseEntity<>(obtainedTodo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);

        return ResponseEntity.ok(updatedTodo);
    }


    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.completeTodo(todoId);

        return ResponseEntity.ok(todoDto);
    }

    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.incompleteTodo(todoId);

        return ResponseEntity.ok(todoDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("Todo deleted successfully");
    }
}
