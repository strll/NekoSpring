package com.myspringframwork.beans.fectory.config;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /**
     * �����е� BeanDefinition ������ɺ�ʵ���� Bean ����֮ǰ���ṩ�޸� BeanDefinition ���ԵĻ���
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
