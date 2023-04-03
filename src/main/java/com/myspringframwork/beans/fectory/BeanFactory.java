package com.myspringframwork.beans.fectory;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {


    /**
     * ���� Bean ��ʵ������
     * @param name Ҫ������bean������
     * @return ʵ������ Bean ����
     * @throws BeansException ���ܻ�ȡ Bean �������׳��쳣
     */
    Object getBean(String name) throws BeansException;

    /**
     * ���غ����캯���� Bean ʵ������
     * @param name Ҫ������bean������
     * @param args ���캯�����
     * @return ʵ������ Bean ����
     * @throws BeansException ���ܻ�ȡ Bean �������׳��쳣
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * ����ָ�����͵Ķ���
     * @param name  Ҫ������bean������
     * @param requiredType ����
     * @param <T>          ����
     * @return             ʵ������ Bean ����
     * @throws BeansException ���ܻ�ȡ Bean �������׳��쳣
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
