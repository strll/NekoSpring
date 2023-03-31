package com.myspringframwork.beans.fectory.support;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.BeanFectory;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFectory {
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = getSingleton(beanName);
        if (singleton!=null){
            return  singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
            return createBean(beanName,beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;
}
