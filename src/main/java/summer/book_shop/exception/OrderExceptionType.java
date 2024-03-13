package summer.book_shop.exception;

public enum OrderExceptionType implements BaseExceptionType {

    NOT_FOUND_ORDER_CODE( 701, "주문 번호를 찾을 수 없습니다."),
    NOT_FOUND_ORDER_ID( 702, "주문자를 찾을 수 없습니다."),
    WRONG_ORDER(703, "주문 접수에 오류가 발생했습니다."),
    CANNOT_DELETE_ORDER(704, "주문 취소 실패하였습니다."),
    CANNOT_UPDATE_ADDRESS(705, "주문 수정에 실패하였습니다."),
    WRONG_PASSWORD(706, "잘못된 주소지 입니다.");


    private final int errorCode;
    private final String errorMessage;

    OrderExceptionType(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}