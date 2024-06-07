package practice.newbalance.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import practice.newbalance.common.dto.ErrorDto;
import practice.newbalance.common.exception.CustomException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDto> handleCustomException(CustomException ex){
        return ErrorDto.toResponseEntity(ex);
    }

}
