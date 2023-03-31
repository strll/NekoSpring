package com.myspringframwork.beans.fectory.support;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    //Ĭ��ʹ��cglib�ķ�ʽ����bean�����ʵ��
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean=null;

        return null;
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
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
//���Ըı�bean�����ʵ����ʹ�õĹ��� ֻ��Ҫʵ��InstantiationStrategy �ӿھͿ���
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
