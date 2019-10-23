package com.ndmitrenko.testingServer.dto.response;

import com.ndmitrenko.testingServer.exception.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DefaultExceptionResponse {
    private String message;
    private HttpStatus httpStatus;
    private ApiResult apiResult;
}
