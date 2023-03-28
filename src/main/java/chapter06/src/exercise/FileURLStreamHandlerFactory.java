package chapter06.src.exercise;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class FileURLStreamHandlerFactory implements URLStreamHandlerFactory {
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (protocol.equals("myftp"))
            return new FileURLStreamHandler();
        else
            return null;
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
