package com.myspringframwork.beans.fectory.support;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
//��Ҫ����bean��ʵ����
public abstract class AbstractAutowireCapableBeanFectory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        //�ڴ���bean��ʱ����˷���֮�⻹�����beanʵ���ŵ�������map��ȥ��
        Object o =null;
        try {
            Class bean = beanDefinition.getBeanClass();
             o = bean.newInstance();
        }catch (BeansException | InstantiationException | IllegalAccessException e){
            return new BeansException("");
        }
        // ���������Դ�� DefaultSingletonBeanRegistry
        registerSingleton(beanName,o);
        return o;
    }
}
