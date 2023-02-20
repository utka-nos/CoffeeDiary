package com.example.dto;

import lombok.Builder;

@Builder
public class ResponseDTO {

    private final int status;
    private final String message;
    private final Object details;

}
