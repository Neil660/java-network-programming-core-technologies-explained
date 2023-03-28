package chapter06.src; /**
 * ��ʾ��δ�����Ӧ���ģ��μ�6.3.3��
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient4 {
    public static void main(String args[]) throws IOException {
        URL url = new URL("http://www.javathinker.net/hello.htm");
        URLConnection connection = url.openConnection();
        //������Ӧ���
        InputStream in = connection.getInputStream(); //��ȡ��Ӧ����
        Class[] types = {String.class, InputStream.class};
        Object obj = connection.getContent(types);
        if (obj instanceof String) {
            System.out.println(obj);
        } else if (obj instanceof InputStream) {
            in = (InputStream) obj;
            FileOutputStream file = new FileOutputStream("data");
            byte[] buff = new byte[1024];
            int len = -1;

            while ((len = in.read(buff)) != -1) {
                file.write(buff, 0, len);
            }

            System.out.println("���ı������");
        } else {
            System.out.println("δ֪����Ӧ��������");
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
