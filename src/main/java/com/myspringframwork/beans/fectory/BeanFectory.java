package com.myspringframwork.beans.fectory;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface  BeanFectory {
     //根据名字获取bean
    public Object getBean(String beanName) throws BeansException;
    public Object getBean(String beanName,Object...args) throws BeansException;


}
