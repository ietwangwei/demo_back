package com.example.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "通用返回对象")
public class ResponseVO<T> {

    @ApiModelProperty(value = "返回码（正确为200）")
    private int code;

    @ApiModelProperty(value = "操作结果的相关信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;
}