package com.phongvu.gof.utils.exception;

public class AppException extends Exception {
    /**
     * Creates a new instance of <code>NewException</code> without detail
     * message.
     */
    public AppException(){
    }

    /**
     * Constructs an instance of <code>NewException</code> with the specified
     * detail message.
     *
     * @param message the detail message.
     */
    public AppException(String message) {
        super(message);
    }


    /**
     * Bao bọc các Exception khác như ReflectionException, IllegalArgumentException, etc.
     * Lưu giữ stack traces để phục vụ mục đích gỡ lỗi/ghi nhật ký.
     *
     * @param message Business error message
     * @param cause   The root cause of the error
     */
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
