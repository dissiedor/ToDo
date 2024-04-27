package com.example.TodoFedorin.Tools;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorCodes {
    UNKNOWN(1, Constants.ValidationMessages.UNKNOWN),
    ID_MUST_BE_POSITIVE(29, Constants.ValidationMessages.ID_MUST_BE_POSITIVE),
    TODO_TEXT_NOT_NULL(31, Constants.ValidationMessages.TODO_TEXT_NOT_NULL),
    TODO_TEXT_SIZE_NOT_VALID(32, Constants.ValidationMessages.TODO_TEXT_SIZE_NOT_VALID),
    TODO_STATUS_NOT_NULL(33, Constants.ValidationMessages.TODO_STATUS_NOT_NULL),
    TASK_NOT_FOUND(34, Constants.ValidationMessages.TASK_NOT_FOUND),
    TASK_PATCH_UPDATED_NOT_CORRECT_COUNT(35, Constants.ValidationMessages.TASK_PATCH_UPDATED_NOT_CORRECT_COUNT),
    TASKS_PAGE_GRATER_OR_EQUAL_1(37, Constants.ValidationMessages.TASKS_PAGE_GREATER_OR_EQUAL_1),
    TASKS_PER_PAGE_GREATER_OR_EQUAL_1(38, Constants.ValidationMessages.TASKS_PER_PAGE_GREATER_OR_EQUAL_1),
    TASKS_PER_PAGE_LESS_OR_EQUAL_100(39, Constants.ValidationMessages.TASKS_PER_PAGE_LESS_OR_EQUAL_100),
    REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT(40, Constants.ValidationMessages.REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT),
    REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT(41, Constants.ValidationMessages.REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(47, Constants.ValidationMessages.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
    private final String message;
    private final int statusCode;

    private static final Map<String, ErrorCodes> codes = new HashMap<>();

    static {
        for (ErrorCodes e : ErrorCodes.values()) {
            codes.put(e.message, e);
        }
    }

    ErrorCodes(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public static ErrorCodes findErrorCodeByMessage(String message) {
        ErrorCodes errorCode = codes.get(message);
        return errorCode != null ? errorCode : ErrorCodes.UNKNOWN;
    }

    BaseSuccessResponse deleteNews(Long id);

    BaseSuccessResponse putNews(Long id, NewsDto newsDto);
}
}
