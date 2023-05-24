package com.myspringframwork.beans.fectory.support.reader;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.core.io.Resource;
import com.myspringframwork.core.io.ResourceLoader;
import com.myspringframwork.beans.fectory.support.beanFactory.BeanDefinitionRegistry;
//读取配置文件中配置的对象属性
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource);
    void loadBeanDefinitions(Resource... resources);
    void loadBeanDefinitions(String location) throws BeansException;
    void loadBeanDefinitions(String... locations) throws BeansException;
}
