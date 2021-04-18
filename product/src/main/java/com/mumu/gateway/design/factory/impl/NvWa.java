package com.mumu.gateway.design.factory.impl;

import com.mumu.gateway.design.factory.AbstractHumanFactory;
import com.mumu.gateway.design.factory.Human;

public class NvWa {
    public static void main(String[] args){
        AbstractHumanFactory ahf = new HumanFactory();
        Human whiteHuman = ahf.createHuman(WhiteHuman.class);

    }
}
