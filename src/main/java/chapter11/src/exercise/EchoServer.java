package chapter11.src.exercise;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EchoServer {
    public static void main(String args[]) {
        try {
            EchoService service = new EchoServiceImpl();

            //����������ע����
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("EchoService", service);

            System.out.println("������ע����һ��EchoService����");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
