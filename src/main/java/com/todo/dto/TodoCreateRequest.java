package com.todo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoCreateRequest {

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    private String content;

    @NotNull(message = "마감일을 선택해 주세요.")
    private LocalDate dueDate;
}
