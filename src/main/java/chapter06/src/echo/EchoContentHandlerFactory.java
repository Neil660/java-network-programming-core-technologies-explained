package chapter06.src.echo;

import java.net.ContentHandler;
import java.net.ContentHandlerFactory;

public class EchoContentHandlerFactory implements ContentHandlerFactory {
    public ContentHandler createContentHandler(String mimetype) {
        if (mimetype.equals("text/plain")) {
            return new EchoContentHandler();
        } else {
            return null;
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
