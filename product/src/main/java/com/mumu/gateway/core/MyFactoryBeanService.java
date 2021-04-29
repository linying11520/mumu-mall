package com.mumu.gateway.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author liny
 * 从BeanFactory获取我们的需要的对象
 *      getBean("myFactoryBean");获取的是MyFactoryBeanService类中getObject方法返回的对象，
 *      getBean("&myFactoryBean");获取的才是MyFactoryBean对象。
 */
@Service
public class MyFactoryBeanService implements BeanFactoryAware {
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @PostConstruct
    public void test(){
        Object myFactoryBean = beanFactory.getBean("myFactoryBean");
        System.out.println(myFactoryBean);
        Object myFactoryBean1 = beanFactory.getBean("&myFactoryBean");
        System.out.println(myFactoryBean1);
    }
}
