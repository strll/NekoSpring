package com.myspringframwork.beans.fectory.config;

//bean����
public class BeanDefinition {
  private Class Bean;

    public BeanDefinition(Class bean) {
        Bean = bean;
    }

    public void setBean(Class bean) {
        Bean = bean;
    }

    public Class getBean() {
        return Bean;
    }

}
