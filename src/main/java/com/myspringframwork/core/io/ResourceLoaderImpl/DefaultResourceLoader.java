package com.myspringframwork.core.io.ResourceLoaderImpl;

import cn.hutool.core.lang.Assert;
import com.myspringframwork.core.io.Resource;
import com.myspringframwork.core.io.ResourceImpl.ClassPathResource;
import com.myspringframwork.core.io.ResourceImpl.FileSystemResource;
import com.myspringframwork.core.io.ResourceImpl.UrlResource;
import com.myspringframwork.core.io.ResourceLoader;

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
