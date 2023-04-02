package com.myspringframwork.beans.fectory.support;

import cn.hutool.core.bean.BeanUtil;
import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.PropertyValue;
import com.myspringframwork.beans.PropertyValues;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    //Ĭ��ʹ��cglib�ķ�ʽ����bean�����ʵ��
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();



        @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
            Object bean = null;
            try {
                bean = createBeanInstance(beanDefinition, beanName, null);
                //��bean�������
                applyPropertyValues(beanName, bean, beanDefinition);
            } catch (Exception e) {
                throw new BeansException("Instantiation of bean failed", e);
            }

            registerSingleton(beanName, bean);
            return bean;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            //��bean�������
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        registerSingleton(beanName, bean);
        return bean;
    }
    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructor=null;
        Class<?> beanclass=beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanclass.getDeclaredConstructors();//��ȡ���еĹ��캯��
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (null!=args&&declaredConstructor.getTypeParameters().length==args.length){
                constructor=declaredConstructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }
protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
try {
    PropertyValues propertyValues = beanDefinition.getPropertyValues();
    for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
        String name = propertyValue.getName();
        Object obj = propertyValue.getValue();
        if (obj instanceof  BeanReference) {
            BeanReference beanReference = (BeanReference) obj;
            obj= getBean(name, beanReference);
        }
        BeanUtil.setFieldValue(bean, name, obj);
    }
} catch (Exception e){
    throw  new BeansException("apply PropertyValues error bean name is ["+beanName+"] error is in  AbstractAutowireCapableBeanFactory",e);
}
}






    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
//���Ըı�bean�����ʵ����ʹ�õĹ��� ֻ��Ҫʵ��InstantiationStrategy �ӿھͿ���
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
