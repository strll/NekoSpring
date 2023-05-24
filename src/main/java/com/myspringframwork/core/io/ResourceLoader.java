package com.myspringframwork.core.io;
//包资源加载器
public interface ResourceLoader {
    String CLASSPATH="classpath:";
    Resource getResource(String location);
}
