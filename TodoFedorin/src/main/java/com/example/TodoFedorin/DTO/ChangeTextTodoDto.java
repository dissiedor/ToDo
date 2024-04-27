package com.example.TodoFedorin.DTO;

import com.example.TodoFedorin.Tools.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeTextTodoDto {
    @Size(
            min = Constants.ValidationNumbers.MIN_TEXT_LENGTH,
            max = Constants.ValidationNumbers.MAX_TEXT_LENGTH,
            message = Constants.ValidationMessages.TODO_TEXT_SIZE_NOT_VALID
    )
    @NotNull(
            message = Constants.ValidationMessages.TODO_TEXT_NOT_NULL
    )
    private String text;

    public void setText(String text) {
        this.text = text.trim();
    }
}