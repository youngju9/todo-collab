package com.todo.service;

import com.todo.dto.TodoCreateRequest;
import com.todo.entity.Todo;
import com.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void createTodo_할일등록성공() {
        // given: 등록할 데이터 준비
        TodoCreateRequest request = new TodoCreateRequest();
        request.setTitle("Spring Boot 공부하기");
        request.setContent("Todo 등록 기능 테스트");
        request.setDueDate(LocalDate.of(2026, 11, 25));

        // when: 할 일 등록
        todoService.createTodo(request);

        // then: DB 저장 결과 검증
        List<Todo> todos = todoRepository.findAll();

        assertThat(todos).hasSize(1);
        assertThat(todos.get(0).getTitle()).isEqualTo("Spring Boot 공부하기");
        assertThat(todos.get(0).getContent()).isEqualTo("Todo 등록 기능 테스트");
        assertThat(todos.get(0).getDueDate()).isEqualTo(LocalDate.of(2026, 11, 25));
        assertThat(todos.get(0).isCompleted()).isFalse();
    }
}
