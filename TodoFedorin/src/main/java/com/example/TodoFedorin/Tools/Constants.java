package com.example.TodoFedorin.Tools;

public interface Constants {
    interface ValidationNumbers {
        int MIN_TEXT_LENGTH = 3;
        int MAX_TEXT_LENGTH = 160;
        int MAX_TASKS_PER_PAGE = 100;
    }

    interface ValidationMessages {
        String UNKNOWN = "unknown";
        String ID_MUST_BE_POSITIVE = "ID must be grater than zero";
        String TODO_TEXT_NOT_NULL = "todo text not null";
        String TODO_TEXT_SIZE_NOT_VALID = "size not valid";
        String TODO_STATUS_NOT_NULL = "todo status not null";
        String TASK_NOT_FOUND = "task not found";
        String TASK_PATCH_UPDATED_NOT_CORRECT_COUNT = "task patch updated not correct count";
        String TASKS_PAGE_GREATER_OR_EQUAL_1 = "task page greater or equal 1";
        String TASKS_PER_PAGE_GREATER_OR_EQUAL_1 = "tasks per page greater or equal 1";
        String TASKS_PER_PAGE_LESS_OR_EQUAL_100 = "tasks per page less or equal 100";
        String REQUIRED_PARAM_IS_NOT_PRESENT = "Parameter %s mustn't be null";
        String REQUIRED_INT_PARAM_PAGE_IS_NOT_PRESENT = "Parameter page mustn't be null";
        String REQUIRED_INT_PARAM_PER_PAGE_IS_NOT_PRESENT = "Parameter perPage mustn't be null";
        String HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "Http request not valid";
    }
}
