package com.myspringframwork.beans.fectory.support.beanFactory.impl;

import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//定义或者注册bean的单例接口
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String, Object> singletonBeanMap=new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {

        return singletonBeanMap.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonBeanMap.put(beanName,singletonObject);
    }
}
