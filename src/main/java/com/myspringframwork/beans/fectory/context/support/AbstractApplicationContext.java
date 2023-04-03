package com.myspringframwork.beans.fectory.context.support;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.core.io.ResourceLoaderImpl.DefaultResourceLoader;
import com.myspringframwork.beans.fectory.ConfigurableListableBeanFactory;
import com.myspringframwork.beans.fectory.config.BeanFactoryPostProcessor;
import com.myspringframwork.beans.fectory.config.BeanPostProcessor;
import com.myspringframwork.beans.fectory.context.ConfigurableApplicationContext;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        //���� BeanFactory ����BeanDefinition ��Ҫ���������� newһ�������� ��ȡxml�������Ϣ ��������Ϣ�ŵ���������Ǹ�map��ȥ
        //�������� DefaultListableBeanFactory
        refreshBeanFactory();
        //��ȡBeanFactory  ��һ���ǻ�ȡ����ļ��ع�bean���ݵĺ�����
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //��ȡbeanʵ��֮ǰ��ǰ�ô���  �޸ĺ����������BeanDefinition����Ϣ
        invokeBeanFactoryPostProcessors(beanFactory);
        //bean����ʵ����֮ǰע��  ��ʱûʲôʵ���ϵ�����(2023.4.3) ��Ҫ�ǰ����beanfactoryɾ���ٷŽ�ȥ
        registerBeanPostProcessors(beanFactory);
        // ʵ����������bean����
        beanFactory.preInstantiateSingletons();

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        //��ȡǰ�ô����bean��ʵ��
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}