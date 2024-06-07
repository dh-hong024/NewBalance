package practice.newbalance.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import practice.newbalance.common.ErrorCode;

@Getter
public class CustomException extends RuntimeException{
    //원하는 HttpStatus로 에러 전달하기 위한 데이터
    private final HttpStatus status;

    //발생한 에러 상황에 대해서 프런트로 코드와 하여 전달하기 위한 데이터
    private final ErrorCode errorCode;

    //명세화하지 않은 에러가 발생한 경우, 발생한 에러에 대한 원인을 전달하기 위한 데이터
    private final String detail;

    public CustomException(HttpStatus status, ErrorCode errorCode){
        this.status = status;
        this.errorCode = errorCode;
        this.detail = "";
    }

    public CustomException(HttpStatus status, ErrorCode errorCode, String detail){
        this.status = status;
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public CustomException(HttpStatus status, ErrorCode errorCode, Throwable cause) {
        this.status = status;
        this.errorCode = errorCode;
        this.detail = cause.getMessage();
    }

    public CustomException(HttpStatus status, CustomException customException) {
        this.status = status;
        this.errorCode = customException.getErrorCode();
        this.detail = customException.getDetail();
    }

    public CustomException(HttpStatus status, Throwable cause) {
        this.status = status;
        this.errorCode = ErrorCode.UNKNOWN;
        this.detail = cause.getMessage();
    }

    public CustomException(Exception exception) {
        if (exception.getClass() == CustomException.class) {
            CustomException customException = (CustomException) exception;
            this.status = customException.getStatus();
            this.errorCode = customException.getErrorCode();
            this.detail = customException.getMessage();
        } else {
            this.status = HttpStatus.BAD_REQUEST;
            this.errorCode = ErrorCode.UNKNOWN;
            this.detail = exception.getMessage();
        }
    }


}
