package com.mumu.gateway.design.factory.impl;

import com.mumu.gateway.design.factory.Human;

public class YellowHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("黄色");
    }

    @Override
    public void run() {
        System.out.println("满");
    }
}
