package com.example.demo.config;

import com.example.demo.exception.AppException;
import com.example.demo.exception.ParamException;
import com.example.demo.util.ResponseUtil;
import com.example.demo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handleException(HttpRequestMethodNotSupportedException e) {
        // log.error("不支持的请求异常：{}", e);
        return ResponseUtil.error("该方法不支持" + e.getMethod() + "请求");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handleException(HttpMessageNotReadableException e) {
        // log.error("不支持的请求异常：{}", e);
        return ResponseUtil.error(e.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    // @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public ResponseVO handleMultipartException(MaxUploadSizeExceededException e) {
        // log.error("MaxUploadSizeExceededException：{}", e);
        if (e.getMessage().contains("exceeds the configured maximum")) {
            return ResponseUtil.error("文件大小不能超过" + e.getMaxUploadSize());
        } else {
            return ResponseUtil.error(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handleBindException(MethodArgumentNotValidException e) {
        // log.error("参数校验异常：{}", e);
        return ResponseUtil.error(ErrorCode.ERROR, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseVO handleException(ServletRequestBindingException e) {
        return ResponseUtil.error(e.getMessage());
    }

    @ExceptionHandler(ParamException.class)
    // @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ResponseVO handleParamException(ParamException e) {
        // log.error("自定义异常：{}", e);
        return ResponseUtil.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(AppException.class)
    // @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ResponseVO handleAppException(AppException e) {
        // log.error("自定义异常：{}", e);
        return ResponseUtil.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    // @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO handleException(Exception e) {
        log.error("未知错误：{}", e);
        return ResponseUtil.error("服务不可用，请稍候重试");
    }


}