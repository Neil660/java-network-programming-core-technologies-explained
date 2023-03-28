package chapter06.src;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient2 {
    public static void main(String args[]) throws IOException {
        URL url = new URL("http://www.javathinker.net/hello.htm");
        URLConnection connection = url.openConnection();
        //������Ӧ���
        System.out.println("�������ͣ�" + connection.getContentType());
        System.out.println("���ĳ��ȣ�" + connection.getContentLength());
        InputStream in = connection.getInputStream(); //��ȡ��Ӧ����

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;

        while ((len = in.read(buff)) != -1) {
            buffer.write(buff, 0, len);
        }

        System.out.println(new String(buffer.toByteArray()));  //���ֽ�����ת��Ϊ�ַ���

    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/