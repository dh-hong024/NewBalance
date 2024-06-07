package practice.newbalance.common.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import practice.newbalance.common.ErrorCode;
import practice.newbalance.common.exception.CustomException;

@Data
@Builder
public class ErrorDto {
    private String code;
    private String msg;
    private String detail;

    public static ResponseEntity<ErrorDto> toResponseEntity(CustomException e){
        ErrorCode errorType = e.getErrorCode();
        String detail = e.getDetail();

        return ResponseEntity.status(e.getStatus())
                .body(
                        ErrorDto.builder()
                                .code(errorType.getCode())
                                .msg(errorType.getMsg())
                                .detail(detail)
                                .build()
                );
    }


}
