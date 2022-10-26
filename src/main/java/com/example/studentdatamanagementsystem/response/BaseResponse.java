package com.example.studentdatamanagementsystem.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private HttpStatus status;
    private String message;
    private T result;
}
