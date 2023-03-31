package com.myspringframwork.beans.fectory.support;

import com.myspringframwork.beans.fectory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args);
}
