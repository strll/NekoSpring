package com.myspringframwork.beans.fectory.support.beanFactory;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
//bean�Ķ���   DefaultListableBeanFactory������ʵ��������ӿ�
public interface BeanDefinitionRegistry {
    //bean������ BeanDefinition������bean��class���� ���ж����ڸ������Ե�ֵ �����propertyValues���list��
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

  BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * �ж��Ƿ����ָ�����Ƶ�BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
       * ����ע��������е�Bean����
     */
    String[] getBeanDefinitionNames();

}
