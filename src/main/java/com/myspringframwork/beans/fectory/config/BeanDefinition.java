package com.myspringframwork.beans.fectory.config;

import com.myspringframwork.beans.PropertyValues;

//bean∂‘œÛ
public class BeanDefinition {
  private Class Bean;

  private PropertyValues propertyValues;

  private String initMethodName;

  private String destroyMethodName;

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public BeanDefinition(Class bean) {
        Bean = bean;
        this.propertyValues=new PropertyValues();
    }
    public BeanDefinition(Class bean,PropertyValues propertyValues) {
        Bean = bean;

        this.propertyValues =propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBean() {
        return Bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBean(Class bean) {
        Bean = bean;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setBeanClass(Class bean) {
        Bean = bean;
    }

    public Class getBeanClass() {
        return Bean;
    }

}
