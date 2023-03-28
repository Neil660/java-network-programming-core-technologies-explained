package chapter11.src.dgc;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleServer {
    public static void main(String args[]) {
        try {
            HelloService service = new HelloServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("HelloService", service);
            System.out.println("������ע����һ��HelloServiceImpl����");

            Thread.sleep(1000);

            registry.unbind("HelloService");
            System.out.println("������ע����һ��HelloServiceImpl����");

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
