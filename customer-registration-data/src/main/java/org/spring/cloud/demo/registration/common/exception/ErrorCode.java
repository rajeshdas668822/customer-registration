package org.spring.cloud.demo.registration.common.exception;

import java.text.MessageFormat;

/**
 * Interface for error code definition
 * Format: [ERR-xxxx]
 */
public interface ErrorCode {

    /**
     * Get error code
     * @return String
     */
    String getCode();

    /**
     * Get the error message
     * @return String
     */
    String getMessage();

    /**
     * Format error message by replacing placeholder with arguments
     * @param errorCode
     * @param args
     * @return String
     */
    static String getFormattedMessage(ErrorCode errorCode, String... args) {
        return MessageFormat.format(errorCode.getMessage(), args);
    }
}
