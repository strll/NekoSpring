package com.myspringframwork.beans.fectory;

import com.myspringframwork.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{


    /**
     * �������ͷ��� Bean ʵ��
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * ����ע��������е�Bean����
     */
    String[] getBeanDefinitionNames();
}
