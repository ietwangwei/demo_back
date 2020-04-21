package com.example.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "PageInfoVO", description = "分页对象")
public class PageInfoVO<T> {

    @ApiModelProperty(value = "总记录数", required = true)
    private int total;

    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize;

    @ApiModelProperty(value = "当前页码", required = true)
    private int pageIndex;

    @ApiModelProperty(value = "总页数", required = true)
    private int totalPage;

    @ApiModelProperty(value = "分页数据", required = true)
    private List<T> list = new ArrayList<>();

    public PageInfoVO(int pageIndex, int pageSize, int total) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        if (pageSize == 0) {
            this.totalPage = 0;
        } else {
            this.totalPage = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
        }
    }

}