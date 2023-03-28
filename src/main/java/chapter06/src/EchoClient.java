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
        //����URLStreamHandlerFactory
        URL.setURLStreamHandlerFactory(new EchoURLStreamHandlerFactory());
        //����ContentHandlerFactory
        URLConnection.setContentHandlerFactory(new EchoContentHandlerFactory());
        URL url = new URL("echo://localhost:8000");
        EchoURLConnection connection = (EchoURLConnection) url.openConnection();
        connection.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connection.getOutputStream(), true);
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msg = br.readLine();
            pw.println(msg);  //�������������Ϣ
            String echoMsg = (String) connection.getContent(); //��ȡ���������ص���Ϣ
            System.out.println(echoMsg);
            if (echoMsg.equals("echo:bye")) {
                connection.disconnect(); //�Ͽ�����
                break;
            }
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
