package chapter13.src.store;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StoreServer {
    public static void main(String args[]) {
        try {
            System.setProperty("java.security.policy", StoreServer.class.getResource("secure.policy").toString());
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            StoreModel storeModel = new StoreModelImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("storeModel", storeModel);
            System.out.println("������ע����StoreModel����");
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
