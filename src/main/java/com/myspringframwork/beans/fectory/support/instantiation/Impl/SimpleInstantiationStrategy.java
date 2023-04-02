package com.myspringframwork.beans.fectory.support.instantiation.Impl;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.support.instantiation.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    //JKD 的实例化
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (null!=constructor){
                //使用有参构造     constructor对象包含类的信息
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else {
                //使用无参构造
                return beanClass.getDeclaredConstructor().newInstance();
            }
        }catch (InvocationTargetException|
                InstantiationException|IllegalAccessException|NoSuchMethodException e){
            throw new BeansException("failed to instantiate "+beanClass.getName()+" faile in SimpleInstantiationStrategy",e);
        }

    }
}
