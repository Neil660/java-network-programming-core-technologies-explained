package chapter02.src;

import java.io.*;
import java.net.*;

public class SendClient {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 8000);
        OutputStream out = s.getOutputStream();
        out.write("hello ".getBytes());
        out.write("everyone".getBytes());
        Thread.sleep(60000);  //˯��1����
        s.close();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
