package com.example.demo.util;
import com.example.demo.config.ErrorCode;
import com.example.demo.vo.ResponseVO;
import org.apache.commons.lang3.StringUtils;

public class ResponseUtil {

    public static ResponseVO success() {
        return error(ErrorCode.SUCCESS);
    }

    public static ResponseVO success(String message) {
        return error(ErrorCode.SUCCESS, message);
    }

    public static ResponseVO data(Object data) {
        return success(null, data);
    }

    public static ResponseVO success(String message, Object data) {
        if (StringUtils.isBlank(message)) {
            message = ErrorCode.SUCCESS.getMessage();
        }
        ResponseVO responseVO = error(ErrorCode.SUCCESS, message);
        responseVO.setData(data);
        return responseVO;
    }

    public static ResponseVO error(String message) {
        return error(ErrorCode.INTERNAL_ERROR, message);
    }

    public static ResponseVO error(ErrorCode errorCode) {
        return error(errorCode, null);
    }

    public static ResponseVO error(int errorCode, String message) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(errorCode);
        responseVO.setMessage(message);
        return responseVO;
    }

    public static ResponseVO error(ErrorCode errorCode, String message) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(errorCode.getCode());
        if (StringUtils.isNotEmpty(message)) {
            responseVO.setMessage(message);
        } else {
            responseVO.setMessage(errorCode.getMessage());
        }
        return responseVO;
    }
}