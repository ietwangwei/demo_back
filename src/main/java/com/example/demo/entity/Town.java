package com.example.demo.entity;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  town
* @author 大狼狗 2020-04-14
*/
@Data
public class Town {

    /**
    * id
    */
    @Id
    private Integer id;

    /**
    * 乡镇名称
    */
    private String name;

    /**
    * 中心坐标经度
    */
    private String lng;

    /**
    * 中心坐标维度
    */
    private String lat;
}
