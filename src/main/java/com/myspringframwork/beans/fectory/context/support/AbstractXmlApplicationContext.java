package com.myspringframwork.beans.fectory.context.support;

import com.myspringframwork.beans.fectory.support.beanFactory.impl.DefaultListableBeanFactory;
import com.myspringframwork.beans.fectory.support.reader.XMLimpl.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext
{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // xmlBeanDefinitionReader ���ڶ�ȡxml�����bean�Ķ�����Ϣ
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(null!=configLocations){
            //��·�������xml�ļ�
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }
    protected abstract String[] getConfigLocations();
}
