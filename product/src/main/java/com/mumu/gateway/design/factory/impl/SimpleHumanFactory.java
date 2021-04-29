package com.mumu.gateway.design.factory.impl;

import com.mumu.gateway.design.factory.Human;

/**
 * 简单工厂模式
 */
public class SimpleHumanFactory {
    public static <T extends Human> T crateHuman(Class<T> c){
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
