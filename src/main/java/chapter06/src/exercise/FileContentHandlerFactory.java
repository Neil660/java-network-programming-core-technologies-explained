package chapter06.src.exercise;

import java.net.ContentHandler;
import java.net.ContentHandlerFactory;

public class FileContentHandlerFactory implements ContentHandlerFactory {
    public ContentHandler createContentHandler(String mimetype) {
        if (mimetype.equals("application/octet-stream")) {
            return new FileContentHandler();
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
