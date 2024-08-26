package com.todo_project.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo_project.todo.model.Todo;
import com.todo_project.todo.repository.RepoTodo;

@Service
public class TodoService {
    @Autowired
    private RepoTodo todorepo;

    public List<Todo> getAllTodos(){
        return todorepo.findAll();
    }

    public Optional<Todo> getTodobyId(Long id){
        return todorepo.findById(id);
    }

    public Todo createTodo(Todo todo){
        System.out.println("Creating Todo with title: " + todo.getTitle() + " and completed: " + todo.isCompleted());
        Todo savedTodo = todorepo.save(todo);
        System.out.println("Saved Todo with ID: " + savedTodo.getId());
        return savedTodo;
    }
    

    public void deleteTodoById(Long Id){
        todorepo.deleteById(Id);
    }

    public Todo updateTodobyId(Long id, Todo todoDetails){
        Todo todo=todorepo.findById(id).orElseThrow();
        todo.setCompleted(todoDetails.isCompleted());
        todo.setTitle(todoDetails.getTitle());
        return todorepo.save(todo);
    }

}
