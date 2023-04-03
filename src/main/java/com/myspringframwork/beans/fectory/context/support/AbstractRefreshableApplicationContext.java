package com.myspringframwork.beans.fectory.context.support;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.fectory.BeanFactory;
import com.myspringframwork.beans.fectory.ConfigurableListableBeanFactory;
import com.myspringframwork.beans.fectory.support.beanFactory.impl.DefaultListableBeanFactory;

//获取bean工厂加载的资源
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
//DefaultListableBeanFactory 是核心类
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
