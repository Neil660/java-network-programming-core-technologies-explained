package chapter06.src.echo;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class EchoURLStreamHandlerFactory implements URLStreamHandlerFactory {
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (protocol.equals("echo"))
            return new EchoURLStreamHandler();
        else
            return null;
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
