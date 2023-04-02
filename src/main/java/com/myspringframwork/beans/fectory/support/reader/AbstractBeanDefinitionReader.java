package com.myspringframwork.beans.fectory.support.reader;

import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.core.io.Resource;
import com.myspringframwork.beans.core.io.ResourceLoader;
import com.myspringframwork.beans.core.io.ResourceLoaderImpl.DefaultResourceLoader;
import com.myspringframwork.beans.fectory.support.beanFactory.BeanDefinitionRegistry;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private ResourceLoader resourceLoader;
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(beanDefinitionRegistry,new DefaultResourceLoader());
    }
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry,ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader=resourceLoader;
    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


}
