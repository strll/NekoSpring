package com.myspringframwork.beans.fectory.support;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

//ºËÐÄÀà
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {


    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition==null){
            throw new BeansException("No Bean named"+beanName+"  DefaultListableBeanFactory beanDefinitionMao don`t have this beanDefinition");
        }
        return beanDefinition;
    }

    @Override   //
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }


}
