package com.myspringframwork.core.io.ResourceImpl;

import com.myspringframwork.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//根据文件获取stream资源
public class FileSystemResource implements Resource {
    private final File file;
    private final String path;

    public FileSystemResource(File file, String path) {
        this.file = file;
        this.path = path;
    }
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }
    public FileSystemResource(String path) {

        this.path = path;
        File file = new File(path);
        this.file=file;
    }
    public final String getPath(){
        return this.path;
    }

    @Override
    public InputStream getInputStream() throws IOException {

        return new FileInputStream(this.file);
    }
}
