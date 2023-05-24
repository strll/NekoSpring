package com.myspringframwork.beans.fectory;

public interface InitializingBean {
    //bean对象属性填充之后调用
    void afterPropertiesSet() throws Exception;
}
