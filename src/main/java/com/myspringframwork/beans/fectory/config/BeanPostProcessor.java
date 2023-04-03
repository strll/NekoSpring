package com.myspringframwork.beans.fectory.config;

import com.myspringframwork.beans.BeansException;

public interface BeanPostProcessor {
    //bean初始化之前要执行的
    Object postProcessBeforeInitialization(Object bean,String beanname) throws BeansException;
    //bean初始化之后要执行的
    Object postProcessAfterInitialization(Object bean,String beanname) throws BeansException;

}
