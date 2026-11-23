package com.todo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.todo.repository.TodoRepository;
import com.todo.dto.TodoCreateRequest;
import com.todo.entity.Todo;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    public void createTodo(TodoCreateRequest request) {
        Todo todo = new Todo(
            request.getTitle(),
            request.getContent(),
            request.getDueDate()
        );

        todoRepository.save(todo);
    }
}
