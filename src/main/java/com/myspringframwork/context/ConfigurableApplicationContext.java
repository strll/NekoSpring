package com.myspringframwork.context;

import com.myspringframwork.beans.BeansException;

public interface ConfigurableApplicationContext  extends ApplicationContext{
    public void refresh() throws BeansException;
}
