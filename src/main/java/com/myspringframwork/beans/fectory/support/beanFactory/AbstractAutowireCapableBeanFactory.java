package com.myspringframwork.beans.fectory.support.beanFactory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.PropertyValue;
import com.myspringframwork.beans.PropertyValues;
import com.myspringframwork.beans.fectory.InitializingBean;
import com.myspringframwork.beans.fectory.config.AutowireCapableBeanFactory;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanPostProcessor;
import com.myspringframwork.beans.fectory.config.BeanReference;
import com.myspringframwork.beans.fectory.support.instantiation.Impl.CglibSubclassingInstantiationStrategy;
import com.myspringframwork.beans.fectory.support.instantiation.InstantiationStrategy;
import javafx.fxml.Initializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    //默认使用cglib的方式创建bean对象的实例
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();



        @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
            Object bean = null;
            try {
                bean = createBeanInstance(beanDefinition, beanName, null);
                //给bean填充属性
                applyPropertyValues(beanName, bean, beanDefinition);

            } catch (Exception e) {
                throw new BeansException("Instantiation of bean failed", e);
            }

            registerSingleton(beanName, bean);
            return bean;
    }

    private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        try{
            invokeInitMethods(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean ["+beanName+"]failed expection is in com/myspringframwork/beans/fectory/support/beanFactory/AbstractAutowireCapableBeanFactory.java",e);
        }
        Object o = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return o;
    }

    private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception{
            if (bean instanceof InitializingBean){
               ((InitializingBean) bean).afterPropertiesSet();
            }
            //避免二次销毁
        String initMethodName = beanDefinition.getInitMethodName();
            if (StrUtil.isNotEmpty(initMethodName)){
                Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
                if (null==method){
                    throw new BeansException("can`t find an init method nameed{"+initMethodName+"}on bean with name{"+beanName+"}"+"this exception is in com/myspringframwork/beans/fectory/support/beanFactory/AbstractAutowireCapableBeanFactory.java");
                }
                method.invoke(bean);
            }
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            //给bean填充属性
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
        Constructor<?>[] declaredConstructors = beanclass.getDeclaredConstructors();//获取所有的构造函数
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
//可以改变bean对象的实例化使用的工具 只需要实现InstantiationStrategy 接口就可以
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

}
