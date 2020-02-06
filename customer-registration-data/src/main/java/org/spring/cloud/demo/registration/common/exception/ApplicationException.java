package org.spring.cloud.demo.registration.common.exception;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String code;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public ApplicationException(ErrorCode errorCode, String... args) {
        super(ErrorCode.getFormattedMessage(errorCode, args));
        this.code = errorCode.getCode();
    }

    public ApplicationException(ErrorCode errorCode, Throwable cause, String... args) {
        super(ErrorCode.getFormattedMessage(errorCode, args), cause);
        this.code = errorCode.getCode();
    }

    public ApplicationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
    }

    public ApplicationException(ErrorCode errorCode, Throwable cause, String errorMessage, boolean replaceMessage) {
        super(errorMessage, cause);
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
