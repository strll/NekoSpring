package com.myspringframwork.core.io;
//����Դ������
public interface ResourceLoader {
    String CLASSPATH="classpath:";
    Resource getResource(String location);
}
