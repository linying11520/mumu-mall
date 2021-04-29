package com.mumu.gateway.design.factory.impl;

import com.mumu.gateway.design.factory.Human;

public class WhiteHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("白色");
    }

    @Override
    public void run() {
        System.out.println("一般");
    }
}
