package chapter06.src.exercise;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class FileURLStreamHandler extends URLStreamHandler {
    public int getDefaultPort() {
        return 8000;
    }

    protected URLConnection openConnection(URL url) throws IOException {
        return new FileURLConnection(url);
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
