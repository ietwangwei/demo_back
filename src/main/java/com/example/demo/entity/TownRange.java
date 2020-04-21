package com.example.demo.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
*  town_range
* @author 大狼狗 2020-04-14
*/
@Data
public class TownRange {
    /**
    * id
    */
    @Id
    private Integer id;

    /**
    * 所属乡镇id
    */
    private Integer townId;

    /**
    * 边界坐标点经度
    */
    private Float lng;

    /**
    * 边界坐标点维度
    */
    private Float lat;



}
