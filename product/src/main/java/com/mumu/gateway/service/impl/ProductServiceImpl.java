package com.mumu.gateway.service.impl;

import com.mumu.gateway.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ProductService{
    @Override
    public Integer addGoods(String goodsName) {
        System.out.println("2");
        return 2;
    }
}
