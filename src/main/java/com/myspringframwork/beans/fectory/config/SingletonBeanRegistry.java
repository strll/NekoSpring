package com.myspringframwork.beans.fectory.config;
//单例对象的注册接口
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    //单例注册表
    void registerSingleton(String beanName,Object singletonObject);
}
