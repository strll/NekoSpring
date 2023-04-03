package common;


import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.PropertyValue;
import com.myspringframwork.beans.PropertyValues;
import com.myspringframwork.beans.fectory.ConfigurableListableBeanFactory;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanFactoryPostProcessor;

/**
 *
 *
 *
 * ���ߣ�DerekYRC https://github.com/DerekYRC/mini-spring
 * @description BeanFactoryPostProcessor ʵ���� Bean ����֮ǰ���޸� BeanDefinition ����
 * @date 2022/03/10
 *
 *
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "��Ϊ���ֽ�����"));
    }

}
