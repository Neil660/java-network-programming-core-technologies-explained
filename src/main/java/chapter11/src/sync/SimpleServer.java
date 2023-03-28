package chapter11.src.sync;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleServer {
    public static void main(String args[]) {
        try {
            Stack stack = new StackImpl("a stack");

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MyStack", stack);

            System.out.println("������ע����һ��Stack����");
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
