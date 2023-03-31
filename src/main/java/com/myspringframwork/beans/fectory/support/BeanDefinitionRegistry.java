package com.myspringframwork.beans.fectory.support;

import com.myspringframwork.beans.fectory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
