package com.myspringframwork.beans.fectory.support.beanFactory;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.BeanFactory;

import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanPostProcessor;
import com.myspringframwork.beans.fectory.config.ConfigurableBeanFactory;
import com.myspringframwork.beans.fectory.support.beanFactory.impl.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName,null);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
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
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
