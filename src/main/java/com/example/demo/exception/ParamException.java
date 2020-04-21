package com.example.demo.exception;

import com.example.demo.config.ErrorCode;
import lombok.Data;

/**
 * 参数异常，比如用户名密码不对，必填参数没有填等
 */
@Data
public class ParamException extends RuntimeException {
    private int errorCode;
    private String message;

    public ParamException(String message) {
        this.errorCode = ErrorCode.ERROR.getCode();
        this.message = message;
    }

    public ParamException(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ParamException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.getCode();
        this.message = message;
    }


}
