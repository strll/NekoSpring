package com.myspringframwork.beans.fectory.support;

import cn.hutool.core.util.StrUtil;
import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.support.beanFactory.DisposableBean;

import java.lang.reflect.Method;

//销毁bean方法的适配器
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
if(bean instanceof DisposableBean){
    ((DisposableBean)bean).destroy();
}
if (StrUtil.isNotEmpty(destroyMethodName)&&!(bean instanceof DisposableBean&&"destroy".equals(this.destroyMethodName))){
    Method method = bean.getClass().getMethod(destroyMethodName);
    if (null==method){
        throw new BeansException("can`t find destory method named ["+destroyMethodName+"]"+"'on bean with name'"+beanName+"'"+"Exception in com/myspringframwork/beans/fectory/support/DisposableBeanAdapter.java");
    }
    method.invoke(bean);
}
    }
}
