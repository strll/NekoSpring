package com.myspringframwork.beans.fectory.support;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
//主要负责bean的实例化
public abstract class AbstractAutowireCapableBeanFectory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object o =null;
        try {
            Class bean = beanDefinition.getBean();
             o = bean.newInstance();
        }catch (BeansException | InstantiationException | IllegalAccessException e){
            return new BeansException("");
        }
        // 这个方法来源于 DefaultSingletonBeanRegistry
        registerSingleton(beanName,o);
        return o;
    }
}
