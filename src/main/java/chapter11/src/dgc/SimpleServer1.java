package chapter11.src.dgc;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleServer1 {
    public static void main(String args[]) {
        try {
            System.setProperty("java.rmi.dgc.leaseValue", "30000");
            HelloService service = new HelloServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("HelloService", service);
            System.out.println("������ע����һ��HelloServiceImpl����");

            //�ȴ��ͻ��˻�ø�Զ�̶��������
            while (!service.isAccessed()) Thread.sleep(500);

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
