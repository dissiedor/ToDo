package com.example.TodoFedorin.DTO.Responses;

import com.example.TodoFedorin.Tools.ErrorCodes;
import lombok.Getter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class BaseSuccessResponse {
    private static final int defaultStatusCode = 0;
    private static final boolean defaultSuccess = true;

    private final boolean success = defaultSuccess;
    private final Integer statusCode;
    private final Set<Integer> codes;

    public BaseSuccessResponse() {
        this(defaultStatusCode);
    }

    public BaseSuccessResponse(Integer statusCode) {
        this.statusCode = statusCode;
        this.codes = Set.of(statusCode);
    }

    public BaseSuccessResponse(Collection<Integer> codes) {
        this.codes = new HashSet<>(codes);
        this.statusCode = this.codes.stream().findFirst().orElse(ErrorCodes.UNKNOWN.getStatusCode());
    }
}