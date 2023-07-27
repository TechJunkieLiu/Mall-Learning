package com.aiyangniu.mall.common.exception;

import com.aiyangniu.mall.common.api.IErrorCode;

/**
 * 自定义API异常
 *
 * @author lzq
 * @date 2023/04/21
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -3904065663573958604L;

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
