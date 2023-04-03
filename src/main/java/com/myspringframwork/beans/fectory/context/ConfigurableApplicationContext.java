package com.myspringframwork.beans.fectory.context;

import com.myspringframwork.beans.BeansException;

public interface ConfigurableApplicationContext  extends ApplicationContext{
    public void refresh() throws BeansException;
}
