package com.myspringframwork.beans.fectory.support.beanFactory;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

import java.rmi.server.ExportException;

//bean的定义   DefaultListableBeanFactory核心类实现了这个接口
public interface BeanDefinitionRegistry {
    //bean的名字 BeanDefinition包含了bean的class对象 还有对象内各个属性的值 存放在propertyValues这个list中
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    //Bean属性填充完的时候使用
void afterPropertiesSet() throws Exception;
  BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
       * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
