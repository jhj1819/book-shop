package summer.book_shop.exception;

public class OrderException extends BaseException{

    private final BaseExceptionType exceptionType;

    public OrderException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}