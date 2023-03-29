package com.myspringframwork.beans.fectory.support;

import com.myspringframwork.beans.BeanException;
import com.myspringframwork.beans.fectory.BeanFectory;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFectory {
    @Override
    public Object getBean(String beanName) throws BeanException {
        Object singleton = getSingleton(beanName);
        if (singleton!=null){
            return  singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
            return beanDefinition;
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeanException;
}
