package com.workshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {
    private String status;
    private T payload;
    private String error;
}
