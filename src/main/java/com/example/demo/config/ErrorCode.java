package com.example.demo.config;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(200, "请求成功"),
    ERROR(400, "请求失败"),
    NOT_LOGIN(401, "未登录，请登录"),
    UNAUTHORIZED(403, "未授权无法访问"),
    NOT_FOUND(404, "找不到请求的资源"),
    INTERNAL_ERROR(500, "服务异常，请稍候重试");
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}