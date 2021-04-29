package com.mumu.gateway.design.factory.impl;

import com.mumu.gateway.design.factory.AbstractHumanFactory;
import com.mumu.gateway.design.factory.Human;

public class HumanFactory extends AbstractHumanFactory {
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;

        try {
            //产生一个人种
            human = (T)Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)human;
    }


}
