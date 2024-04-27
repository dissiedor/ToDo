package com.example.TodoFedorin.Controler;

import com.example.TodoFedorin.DTO.CreateTodoDto;
import com.example.TodoFedorin.DTO.GetNewsDto;
import com.example.TodoFedorin.DTO.Responses.CustomSuccessResponse;
import com.example.TodoFedorin.Entity.TasksEntity;
import com.example.TodoFedorin.Service.TodoService;
import com.example.TodoFedorin.Tools.Constants;
import com.example.TodoFedorin.DTO.Responses.BaseSuccessResponse;
import com.example.TodoFedorin.DTO.ChangeTextTodoDto;
import com.example.TodoFedorin.DTO.ChangeStatusTodoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@Validated
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<GetNewsDto<List<TasksEntity>>>> getAll(
            @RequestParam
            @PositiveOrZero(message = Constants.ValidationMessages.TASKS_PAGE_GREATER_OR_EQUAL_1) Long page,
            @RequestParam
            @Positive(message = Constants.ValidationMessages.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
            @Max(value = Constants.ValidationNumbers.MAX_TASKS_PER_PAGE, message = Constants.ValidationMessages.TASKS_PER_PAGE_LESS_OR_EQUAL_100) Long perPage,
            @RequestParam(required = false) Boolean status) {
        return ResponseEntity.ok(service.findAll(page, perPage, status));
    }

    @PostMapping
    public ResponseEntity<CustomSuccessResponse<TasksEntity>> create(@Valid @RequestBody CreateTodoDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PatchMapping("/text/{id}")
    public ResponseEntity<BaseSuccessResponse> updateTextById(
            @PathVariable
            @PositiveOrZero(message = Constants.ValidationMessages.ID_MUST_BE_POSITIVE) Long id,
            @Valid
            @RequestBody ChangeTextTodoDto dto) {
        return ResponseEntity.ok(service.updateTextById(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<BaseSuccessResponse> deleteAll() {
        return ResponseEntity.ok(service.deleteAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseSuccessResponse> deleteById(
            @PathVariable
            @PositiveOrZero(message = Constants.ValidationMessages.ID_MUST_BE_POSITIVE) Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PatchMapping
    public ResponseEntity<BaseSuccessResponse> updateStatusAll(@Valid @RequestBody ChangeStatusTodoDto dto) {
        return ResponseEntity.ok(service.updateStatusAll(dto));
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<BaseSuccessResponse> updateStatusById(
            @PathVariable
            @PositiveOrZero(message = Constants.ValidationMessages.ID_MUST_BE_POSITIVE) Long id,
            @Valid
            @RequestBody ChangeStatusTodoDto dto) {
        return ResponseEntity.ok(service.updateStatusById(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.deleteNews(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity putNews(
            @PathVariable Long id,
            @Valid @RequestBody NewsDto newsDto) {
        return ResponseEntity.ok(newsService.putNews(id, newsDto));
    }
}
