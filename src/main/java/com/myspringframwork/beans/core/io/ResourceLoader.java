package com.myspringframwork.beans.core.io;
//����Դ������
public interface ResourceLoader {
    String CLASSPATH="classpath:";
    Resource getResource(String location);
}
