package com.myspringframwork.beans.fectory.support.reader.XMLimpl;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.core.util.StrUtil;
import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.PropertyValue;
import com.myspringframwork.beans.core.io.Resource;
import com.myspringframwork.beans.core.io.ResourceLoader;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanReference;
import com.myspringframwork.beans.fectory.support.beanFactory.BeanDefinitionRegistry;
import com.myspringframwork.beans.fectory.support.reader.AbstractBeanDefinitionReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }
    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry,resourceLoader);
    }
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }


    public void  doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element documentElement = document.getDocumentElement();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i=0;i<childNodes.getLength();i++){
            if(!(childNodes.item(i) instanceof Element)) continue;
            //ֻ����bean����
            if (! "bean".equals (childNodes.item(i).getNodeName())) continue;

            Element bean = (Element) childNodes.item(i);

            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String aClass = bean.getAttribute("class");
            Class<?> aClass1 = Class.forName(aClass);
            //����bean������    //����ʹ��id
            String beanName= StrUtil.isNotEmpty(id)? id :name;
            //��������еĻ��Ͱ�����Сдһ�¾ͺ�
            if (StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(beanName);
            }
            //�������bean
            BeanDefinition beanDefinition = new BeanDefinition(aClass1);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
                //��ȡxml�����bean ������
                if (! "property".equals (bean.getChildNodes().item(j).getNodeName())) continue;
                Element property = (Element) bean.getChildNodes().item(j);
                String propertynName = property.getAttribute("name");
                String propertyValue = property.getAttribute("value");
                String propertyRef = property.getAttribute("ref");
                //��value�ǲ��ǻ�����������  ����Ƕ���Ļ���newһ��BeanReference
                //BeanReferenceֻ��һ��������   private final String beanName; ��¼��bean������
                //��� PropertyValue ��value��BeanReference������ô��ȥ�������valueָ���Ķ���

                Object value = StrUtil.isNotEmpty(propertyRef) ?
                        new BeanReference(propertyRef) : propertyValue;
                PropertyValue propertyValue1 = new PropertyValue(propertynName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue1);
            }
            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException( "beanName["+beanName+"] is not allowed"+ "Exception in XmlBeanDefinitionReader");
            }
            //������ŵ���������Ǹ�map��ȥ
            getRegistry().registerBeanDefinition(beanName,beanDefinition);

        }


    }

}
