package com.myspringframwork.beans.fectory.config;
//���������ע��ӿ�
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    //����ע���
    void registerSingleton(String beanName,Object singletonObject);
}
