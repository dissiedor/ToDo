package com.example.TodoFedorin.Service;

import com.example.TodoFedorin.DTO.CreateTodoDto;
import com.example.TodoFedorin.DTO.GetNewsDto;
import com.example.TodoFedorin.DTO.Responses.CustomSuccessResponse;
import com.example.TodoFedorin.Entity.TasksEntity;
import com.example.TodoFedorin.Exeption.CustomException;
import com.example.TodoFedorin.Repository.TodoRepository;
import com.example.TodoFedorin.DTO.Responses.BaseSuccessResponse;
import com.example.TodoFedorin.DTO.ChangeTextTodoDto;
import com.example.TodoFedorin.DTO.ChangeStatusTodoDto;
import com.example.TodoFedorin.Tools.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public CustomSuccessResponse<GetNewsDto<List<TasksEntity>>> findAll(Long page, Long perPage, Boolean status) {
        Pageable pageable = PageRequest.of(page.intValue(), perPage.intValue());
        Page<TasksEntity> tasksPages;
        if (status != null) {
            tasksPages = repository.findAllByStatus(status, pageable);
        } else {
            tasksPages = repository.findAll(pageable);
        }

        List<TasksEntity> tasks = tasksPages.getContent();
        long isDone = repository.countByStatus(true);
        long isNotDone = repository.countByStatus(false);

        GetNewsDto<List<TasksEntity>> dto = new GetNewsDto<>(tasks, isNotDone, tasksPages.getTotalElements(), isDone);
        return new CustomSuccessResponse<>(dto);
    }

    public CustomSuccessResponse<TasksEntity> create(CreateTodoDto dto) {
        TasksEntity tasksEntity = TasksEntity
                .builder()
                .text(dto.getText())
                .status(false)
                .build();
        return new CustomSuccessResponse<>(repository.save(tasksEntity));
    }

    @Transactional
    public BaseSuccessResponse deleteAll() {
        repository.deleteAll();
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse deleteById(Long id) {
        Optional<TasksEntity> optionalTask = repository.findById(id);
        TasksEntity task = optionalTask.orElseThrow(() -> new CustomException(ErrorCodes.TASK_NOT_FOUND.getMessage(), ErrorCodes.TASK_NOT_FOUND.getStatusCode()));

        repository.deleteById(task.getId());
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse updateStatusById(Long id, ChangeStatusTodoDto dto) {
        Optional<TasksEntity> optionalTask = repository.findById(id);
        TasksEntity task = optionalTask.orElseThrow(() -> new CustomException(ErrorCodes.TASK_NOT_FOUND.getMessage(), ErrorCodes.TASK_NOT_FOUND.getStatusCode()));
        task.setStatus(dto.getStatus());
        repository.save(task);
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse updateStatusAll(ChangeStatusTodoDto dto) {
        List<TasksEntity> tasks = repository.findAll();
        tasks.forEach(t -> t.setStatus(dto.getStatus()));
        repository.saveAll(tasks);
        return new BaseSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse updateTextById(Long id, ChangeTextTodoDto dto) {
        Optional<TasksEntity> optionalTask = repository.findById(id);
        TasksEntity task = optionalTask.orElseThrow(() -> new CustomException(ErrorCodes.TASK_NOT_FOUND.getMessage(), ErrorCodes.TASK_NOT_FOUND.getStatusCode()));

        task.setText(dto.getText());
        repository.save(task);
        return new BaseSuccessResponse();
    }

    @Override
    public BaseSuccessResponse deleteNews(Long id) {
        NewsEntity newsEntity = newsRepository.findById(id)
                .orElseThrow(() -> new ApException(ValidationConstants.NEWS_NOT_FOUND));
        newsRepository.delete(newsEntity);
        return BaseSuccessResponse.getOkResponse();
    }

    @Override
    public BaseSuccessResponse putNews(Long id, NewsDto newsDto) {
        NewsEntity newsEntity = newsRepository.findById(id)
                .orElseThrow(() -> new ApException(ValidationConstants.NEWS_NOT_FOUND));
        NewsEntity updatedNewsEntity = newsMapper. newsDtoToNewsEntity(newsDto);

        newsEntity.setTitle(updatedNewsEntity.getTitle());
        newsEntity.setDescription(updatedNewsEntity.getDescription());
        newsEntity.setImage(updatedNewsEntity.getImage());
        newsEntity.setTags(updatedNewsEntity.getTags());
        newsRepository.save(newsEntity);
        return BaseSuccessResponse.getOkResponse();
    }
}
