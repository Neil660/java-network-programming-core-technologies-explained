package chapter06.src;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpClient1 {
    public static void main(String args[]) throws IOException {
        URL url = new URL("http://www.javathinker.net/hello.htm");

        //������Ӧ���
        InputStream in = url.openStream();
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