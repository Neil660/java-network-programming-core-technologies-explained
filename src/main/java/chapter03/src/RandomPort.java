package chapter03.src;

import java.io.*;
import java.net.*;

public class RandomPort {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        System.out.println("�����Ķ˿�Ϊ:" + serverSocket.getLocalPort());
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
