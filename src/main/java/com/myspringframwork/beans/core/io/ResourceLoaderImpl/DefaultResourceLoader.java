package com.myspringframwork.beans.core.io.ResourceLoaderImpl;

import cn.hutool.core.lang.Assert;
import com.myspringframwork.beans.core.io.Resource;
import com.myspringframwork.beans.core.io.ResourceImpl.ClassPathResource;
import com.myspringframwork.beans.core.io.ResourceImpl.FileSystemResource;
import com.myspringframwork.beans.core.io.ResourceImpl.UrlResource;
import com.myspringframwork.beans.core.io.ResourceLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"Location can't be null fail in DefaultResourceLoader ");
        if (location.startsWith(CLASSPATH)){
            return new ClassPathResource(location.substring(CLASSPATH.length()));
        }else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }

    }
}
