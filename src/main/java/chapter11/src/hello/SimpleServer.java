package chapter11.src.hello;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleServer {
    public static void main(String args[]) {
        try {
            HelloService service1 = new HelloServiceImpl("service1");
            HelloService service2 = new HelloServiceImpl("service2");

            //����������ע����
            Registry registry = LocateRegistry.createRegistry(1099);
            //Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("HelloService1", service1);
            registry.rebind("HelloService2", service2);

            System.out.println("������ע��������HelloService����");
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
