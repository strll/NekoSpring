package common;

import bean.UserService;
import com.myspringframwork.beans.BeansException;
import com.myspringframwork.beans.PropertyValue;
import com.myspringframwork.beans.PropertyValues;
import com.myspringframwork.beans.fectory.ConfigurableListableBeanFactory;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanFactoryPostProcessor;
import com.myspringframwork.beans.fectory.config.BeanPostProcessor;

/**
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * @description BeanPostProcessor 在 Bean 对象执行初始化方法前后进行扩展
 * @date 2022/03/10
 *
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
