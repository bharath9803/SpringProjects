package com.todo_project.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo_project.todo.model.Todo;
import com.todo_project.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodobyId(id)
            .map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
    System.out.println("Received Todo: " + todo.getTitle() + ", Completed: " + todo.isCompleted());
    Todo createdTodo = todoService.createTodo(todo);
    System.out.println("Created Todo: " + createdTodo.getTitle() + ", ID: " + createdTodo.getId());
    return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
}


    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        return new ResponseEntity<>(todoService.updateTodobyId(id, todoDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
public ResponseEntity<String> saveTodo(@RequestBody Todo todo) {
    // Save the Todo object to the database
    Todo savedTodo = todoService.createTodo(todo);

    // Optionally, you can include information about the saved todo in the response
    return ResponseEntity.ok().body("Todo saved successfully with ID: " + savedTodo.getId());
}

}
