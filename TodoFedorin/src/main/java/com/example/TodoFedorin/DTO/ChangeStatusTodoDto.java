package com.example.TodoFedorin.DTO;

import com.example.TodoFedorin.Tools.Constants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusTodoDto {
    @NotNull(message = Constants.ValidationMessages.TODO_STATUS_NOT_NULL)
    private Boolean status;
}
