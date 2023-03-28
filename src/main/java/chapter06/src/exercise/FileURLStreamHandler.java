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
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/
