package com.example.demo.exception;

import com.example.demo.config.ErrorCode;
import lombok.Data;

/**
 * 程序异常，比如读取文件失败
 */
@Data
public class AppException extends RuntimeException {
    private int errorCode;
    private String message;

    public AppException(String message) {
        this.errorCode = ErrorCode.ERROR.getCode();
        this.message = message;
    }

}
