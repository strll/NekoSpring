package com.myspringframwork.beans.fectory.config;

import com.myspringframwork.beans.BeansException;

public interface BeanPostProcessor {
    //bean��ʼ��֮ǰҪִ�е�
    Object postProcessBeforeInitialization(Object bean,String beanname) throws BeansException;
    //bean��ʼ��֮��Ҫִ�е�
    Object postProcessAfterInitialization(Object bean,String beanname) throws BeansException;

}
