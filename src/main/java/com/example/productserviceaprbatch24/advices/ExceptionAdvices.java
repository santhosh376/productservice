package com.example.productserviceaprbatch24.advices;


import com.example.productserviceaprbatch24.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRunTimeException(RuntimeException e){
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setStatus("ERROR");
        dto.setMessage(e.getMessage());
        return dto;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(){
        return "something went wrong";
    }
}
