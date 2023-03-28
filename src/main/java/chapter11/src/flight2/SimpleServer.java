package chapter11.src.flight2;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleServer {
    public static void main(String args[]) {
        try {
            FlightFactory factory = new FlightFactoryImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FlightFactory", factory);

            System.out.println("������ע����һ��FlightFactory����");
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
