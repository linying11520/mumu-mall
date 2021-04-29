package com.mumu.gateway.design.factory.impl;

import com.mumu.gateway.design.factory.Human;

public class BlackHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("黑色");
    }

    @Override
    public void run() {
        System.out.println("快");
    }
}
