package com.myspringframwork.beans;

//是具体的属性信息 比如说 String age;  这里面的name 就是age  value就是age的数值
public class PropertyValue {

    /** 属性名称 */
    private final String name;

    /** 属性值 */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


}
