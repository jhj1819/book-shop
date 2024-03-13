package summer.book_shop.exception;

public enum UserExceptionType implements BaseExceptionType {

    NOT_FOUND_MEMBER(501, "사용자를 찾을 수 없습니다."),
    ALREADY_EXIST_ID(502, "이미 존재하는 아이디입니다."),
    ALREADY_EXIST_NICKNAME(503, "이미 존재하는 닉네임입니다."),
    CANNOT_REGISTER(504, "가입 조건이 만족되지 않았습니다."),
    WRONG_PASSWORD(510, "잘못된 비밀번호입니다."),
    NOT_EQUAL_PASSWORD(511, "비밀번호가 일치하지 않습니다.");

    private final int errorCode;
    private final String errorMessage;

    UserExceptionType(int errorCode, String errorMessage) {
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
