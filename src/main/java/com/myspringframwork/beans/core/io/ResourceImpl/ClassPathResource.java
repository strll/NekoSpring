package com.myspringframwork.beans.core.io.ResourceImpl;

import cn.hutool.core.lang.Assert;
import com.myspringframwork.beans.core.io.Resource;
import com.myspringframwork.beans.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
public class ClassPathResource implements Resource {
    private final String url;
    private ClassLoader classLoader;
    public ClassPathResource(String url) {
         this(url, (ClassLoader) null);
    }

    public ClassPathResource(String url, ClassLoader classLoader) {
        Assert.isNull(classLoader,"path can't to be null");
        this.url = url;
        this.classLoader = (classLoader!=null ? classLoader: ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(url);
        if (resourceAsStream==null){
            throw new FileNotFoundException(this.url+" this path cant be opened beacuse it's not exist this fail is in ClassPathResource");
        }
        return resourceAsStream;
    }
}
