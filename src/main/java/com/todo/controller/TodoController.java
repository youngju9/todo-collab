package com.todo.controller;

import com.todo.dto.TodoCreateRequest;
import com.todo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.todo.entity.Todo;
@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public String showTodoList(Model model) {
        List<Todo> todos = todoService.getTodos();
        model.addAttribute("todos", todos);
        return "todos/list";
    }

    @GetMapping("/{id}")
    public String showTodoDetail(@PathVariable("id") Long id, Model model) {
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        return "todos/detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("todoCreateRequest", new TodoCreateRequest());
        return "todos/new";
    }

    @PostMapping
    public String createTodo(
            @Valid @ModelAttribute("todoCreateRequest") TodoCreateRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "todos/new";
        }

        todoService.createTodo(request);
        return "redirect:/todos";
        
    }

    @PostMapping("/{id}/delete")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}
