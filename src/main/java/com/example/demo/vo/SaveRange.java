package com.example.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SaveRange {
    Long townId;
    List<LngLat> points;
}
