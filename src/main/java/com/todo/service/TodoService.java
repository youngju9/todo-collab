package com.todo.service;

import java.util.List;

import org.springframework.data.domain.Sort;
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

    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
