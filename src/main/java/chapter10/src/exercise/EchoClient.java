package chapter10.src.exercise;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoClient {
    ProxyFactory proxyFactory;
    EchoService echoService;

    public EchoClient() throws Exception {
        proxyFactory = new ProxyFactory("localhost", 8000);
        //������̬������ʵ��
        echoService =
                (EchoService) proxyFactory.getProxy(EchoService.class);
    }

    public static void main(String args[]) throws Exception {
        new EchoClient().talk();
    }

    public void talk() throws Exception {
        try {
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = localReader.readLine()) != null) {
                System.out.println(echoService.echo(msg));

                if (msg.equals("bye"))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            proxyFactory.close();
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
