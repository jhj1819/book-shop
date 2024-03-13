package summer.book_shop.exception;

public class UserException extends BaseException {

    private final BaseExceptionType exceptionType;

    public UserException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
