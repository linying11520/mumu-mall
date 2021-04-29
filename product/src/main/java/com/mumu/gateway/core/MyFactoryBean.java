package com.mumu.gateway.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author Admliny
 * 自定义FactoryBean
 */
@Service
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
       return "这个是自定义的FactoryBean";
    }


    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
