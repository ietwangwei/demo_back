package com.example.demo.controller;

import com.example.demo.dao.TownDao;
import com.example.demo.entity.TownRange;
import com.example.demo.util.ResponseUtil;
import com.example.demo.vo.LngLat;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.SaveRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/town")
public class TownController {
    @Autowired
    private TownDao townDao;

    @GetMapping("/get")
    public ResponseVO get() {
        System.out.println(townDao.selectAll());
        return ResponseUtil.success();
    }

    @GetMapping("/getRange/{townId}")
    public ResponseVO getRange(@PathVariable Long townId) {
        List<TownRange> byTownId = townDao.getByTownId(townId);
        List<LngLat> lngLats = new ArrayList<>();
        byTownId.forEach(townRange -> {
            LngLat lngLat = new LngLat();
            lngLat.setLat(townRange.getLat());
            lngLat.setLng(townRange.getLng());
            lngLats.add(lngLat);
        });
        return ResponseUtil.data(lngLats);
    }

    @PostMapping("/saveRange")
    public ResponseVO saveRange(@RequestBody SaveRange vo) {
        Long townId = vo.getTownId();
        townDao.deleteRange(townId);
        townDao.insertRanges(vo);
        return ResponseUtil.success();
    }
}
