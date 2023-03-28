package chapter06.src;


import chapter06.src.echo.EchoContentHandlerFactory;
import chapter06.src.echo.EchoURLConnection;
import chapter06.src.echo.EchoURLStreamHandlerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class EchoClient {
    public static void main(String args[]) throws IOException {
        //设置URLStreamHandlerFactory
        URL.setURLStreamHandlerFactory(new EchoURLStreamHandlerFactory());
        //设置ContentHandlerFactory
        URLConnection.setContentHandlerFactory(new EchoContentHandlerFactory());
        URL url = new URL("echo://localhost:8000");
        EchoURLConnection connection = (EchoURLConnection) url.openConnection();
        connection.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connection.getOutputStream(), true);
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msg = br.readLine();
            pw.println(msg);  //向服务器发送消息
            String echoMsg = (String) connection.getContent(); //读取服务器返回的消息
            System.out.println(echoMsg);
            if (echoMsg.equals("echo:bye")) {
                connection.disconnect(); //断开连接
                break;
            }
        }
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/
