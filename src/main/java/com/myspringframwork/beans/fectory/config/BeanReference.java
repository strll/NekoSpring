package com.myspringframwork.beans.fectory.config;
//BeanReference����Ϊ
//PropertyValue �е�value������ֵ���ڵ� ��� ����BeanReference���͵Ļ�
//��ô������Ծ���һ������
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
