package com.example.TodoFedorin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetNewsDto<T> {
    private T content;
    private long notReady;
    private long numberOfElements;
    private long ready;
}
