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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void getTodos_빈목록조회() {
        // given: 아무것도 등록하지 않은 상태

        // when: 목록 조회
        List<Todo> todos = todoService.getTodos();

        // then: 빈 목록이 반환됨
        assertThat(todos).isEmpty();
    }

    @Test
    void getTodos_목록조회성공() {
        // given: 할 일 2개 등록
        todoService.createTodo(createRequest("첫 번째 할 일"));
        todoService.createTodo(createRequest("두 번째 할 일"));

        // when: 목록 조회
        List<Todo> todos = todoService.getTodos();

        // then: 등록한 2개가 모두 조회됨
        assertThat(todos).hasSize(2);
    }

    @Test
    void getTodos_최신등록순정렬() throws InterruptedException {
        // given: 할 일을 시간 차이를 두고 순서대로 등록
        todoService.createTodo(createRequest("먼저 등록한 할 일"));
        Thread.sleep(10);
        todoService.createTodo(createRequest("나중에 등록한 할 일"));

        // when: 목록 조회
        List<Todo> todos = todoService.getTodos();

        // then: 나중에 등록한 할 일이 첫 번째로 옴
        assertThat(todos.get(0).getTitle()).isEqualTo("나중에 등록한 할 일");
        assertThat(todos.get(1).getTitle()).isEqualTo("먼저 등록한 할 일");
    }

    @Test
    void deleteTodo_삭제성공() {
        // given: 할 일 2개 등록 후 삭제할 할 일의 id 찾기
        todoService.createTodo(createRequest("삭제할 일"));
        todoService.createTodo(createRequest("남을 일"));
        Long deleteId = todoService.getTodos().stream()
                .filter(todo -> todo.getTitle().equals("삭제할 일"))
                .findFirst()
                .orElseThrow()
                .getId();

        // when: 할 일 삭제
        todoService.deleteTodo(deleteId);

        // then: 삭제한 할 일은 없고 나머지 1개만 남음
        List<Todo> todos = todoService.getTodos();
        assertThat(todos).hasSize(1);
        assertThat(todos.get(0).getTitle()).isEqualTo("남을 일");
    }

    @Test
    void deleteTodo_없는할일삭제시예외() {
        // given: 존재하지 않는 id
        Long notExistId = 999L;

        // when & then: 삭제하면 예외 발생
        assertThatThrownBy(() -> todoService.deleteTodo(notExistId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 할 일이 없습니다");
    }

    // 테스트용 등록 요청 데이터를 만드는 도우미 메서드
    private TodoCreateRequest createRequest(String title) {
        TodoCreateRequest request = new TodoCreateRequest();
        request.setTitle(title);
        request.setContent("테스트 내용");
        request.setDueDate(LocalDate.of(2026, 11, 30));
        return request;
    }
}
