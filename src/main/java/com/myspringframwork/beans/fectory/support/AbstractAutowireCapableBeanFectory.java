package com.myspringframwork.beans.fectory.support;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
//主要负责bean的实例化
public abstract class AbstractAutowireCapableBeanFectory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        //在创建bean的时候除了返回之外还把这个bean实例放到单例的map中去了
        Object o =null;
        try {
            Class bean = beanDefinition.getBeanClass();
             o = bean.newInstance();
        }catch (BeansException | InstantiationException | IllegalAccessException e){
            return new BeansException("");
        }
        // 这个方法来源于 DefaultSingletonBeanRegistry
        registerSingleton(beanName,o);
        return o;
    }
}
