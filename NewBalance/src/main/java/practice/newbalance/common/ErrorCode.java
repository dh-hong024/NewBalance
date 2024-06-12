package practice.newbalance.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //400
    UNKNOWN("UNKNOWN", "알 수 없는 에러가 발생하였습니다"),
    NOT_EXISTED_EMAIL("NOT_EXISTED_EMAIL", "존재하지 않는 회원입니다."),
    WRONG_PASSWORD("WRONG_PASSWORD", "틀린 비밀번호 입니다."),
    NOT_EXISTED_DATA("NOT_EXISTED_DATA", "데이터 조회에 실패했습니다."),
    
    //상품관련 에러 코드
    OUT_OF_STOCK("OUT_OF_STOCK", "상품 주문 수량이 재고 수량보다 많습니다.");
    //401

    //403

    private final String code;
    private final String msg;
}
