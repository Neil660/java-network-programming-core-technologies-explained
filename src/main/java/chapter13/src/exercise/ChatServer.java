package chapter13.src.exercise;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer {
    public static void main(String args[]) {
        try {
            System.setProperty("java.security.policy", ChatServer.class.getResource("secure.policy").toString());
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            ChatModel chatModel = new ChatModelImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("chatModel", chatModel);
            System.out.println("������ע����ChatModel����");
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
