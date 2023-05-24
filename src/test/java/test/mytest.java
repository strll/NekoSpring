package test;

import bean.UserDao;
import bean.UserService;
import cn.hutool.core.io.IoUtil;
import com.myspringframwork.beans.PropertyValue;
import com.myspringframwork.beans.PropertyValues;
import com.myspringframwork.core.io.Resource;
import com.myspringframwork.core.io.ResourceLoaderImpl.DefaultResourceLoader;
import com.myspringframwork.beans.fectory.config.BeanDefinition;
import com.myspringframwork.beans.fectory.config.BeanReference;
import com.myspringframwork.context.support.ClassPathXmlApplicationContext;
import com.myspringframwork.beans.fectory.support.beanFactory.impl.DefaultListableBeanFactory;
import com.myspringframwork.beans.fectory.support.reader.XMLimpl.XmlBeanDefinitionReader;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class mytest {
    @Test
    public void test1(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }


    @Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"小傅哥"});
        System.out.println(obj);
    }

    @Test
    public void test_newInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("wxt");
        System.out.println(userService);
    }

    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> beanClass = UserService.class;
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];
        Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService userService = declaredConstructor.newInstance("wxt");
        System.out.println(userService);
    }
@Test
    public void testbeanadd(){
    // 1.初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2. UserDao 注册
    beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

    // 3. UserService 设置属性[uId、userDao]
    PropertyValues propertyValues = new PropertyValues();
    propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
    propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

    // 4. UserService 注入bean
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
    beanFactory.registerBeanDefinition("userService", beanDefinition);

    // 5. UserService 获取bean
    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
}



//    @Before
//    public void init() {
//        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
//    }

    @Test
    public void test_classpath() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("src/main/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


    @org.junit.Test
    public void test_xml1() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


}
