package com.myspringframwork.beans.core.io;

import java.io.IOException;
import java.io.InputStream;
//��Դ���ؽӿ�
public interface Resource {
    InputStream getInputStream () throws IOException;
}
