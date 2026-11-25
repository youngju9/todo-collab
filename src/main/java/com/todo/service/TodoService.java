package com.todo.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.todo.repository.TodoRepository;
import com.todo.dto.TodoCreateRequest;
import com.todo.entity.Todo;
import com.todo.exception.TodoNotFoundException;

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

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다. id=" + id));
        todoRepository.delete(todo);
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException("존재하지 않는 할 일입니다."));
    }
}
