package chapter06.src; /**
 * 演示如何处理响应正文，参见6.3.3节
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
        //接收响应结果
        InputStream in = connection.getInputStream(); //读取响应正文
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

            System.out.println("正文保存完毕");
        } else {
            System.out.println("未知的响应正文类型");
        }
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/
