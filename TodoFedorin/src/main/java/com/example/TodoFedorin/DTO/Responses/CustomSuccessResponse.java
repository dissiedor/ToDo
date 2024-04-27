package com.example.TodoFedorin.DTO.Responses;

import lombok.Getter;

@Getter
public class CustomSuccessResponse<T> extends BaseSuccessResponse{
    private final T data;
    public CustomSuccessResponse(T data) {
        this.data = data;
    }
}