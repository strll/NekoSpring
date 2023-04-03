package com.myspringframwork.beans.fectory.config;

import com.myspringframwork.beans.BeansException;

public interface AutowireCapableBeanFactory {

    /**
     * ִ�� BeanPostProcessors �ӿ�ʵ����� postProcessBeforeInitialization ����
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * ִ�� BeanPostProcessors �ӿ�ʵ����� postProcessorsAfterInitialization ����
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;


}
