package com.mumu.gateway.controller;

import com.mumu.gateway.module.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class GoodsController {

    @PostMapping("/save")
    public Goods save(@RequestBody Goods goods) {
        System.out.print(goods);
        goods.setUpdateTime(new Date());
        return goods;
    }
}
