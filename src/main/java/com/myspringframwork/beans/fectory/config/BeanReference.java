package com.myspringframwork.beans.fectory.config;
//BeanReference是作为
//PropertyValue 中的value的属性值存在的 如果 它是BeanReference类型的话
//那么这个属性就是一个对象
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
