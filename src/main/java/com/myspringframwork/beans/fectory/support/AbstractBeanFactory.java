package com.myspringframwork.beans.fectory.support;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.BeanFectory;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFectory {
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName,null);
    }


    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
