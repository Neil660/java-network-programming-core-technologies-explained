package chapter13.src.exercise;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;

public class ChatClient {
    public static void main(String args[]) {
        System.setProperty("java.security.policy", ChatClient.class.getResource("secure.policy").toString());
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            ChatModel model;
            ChatView view;
            ChatController ctrl;

            Registry registry = LocateRegistry.getRegistry(1099);
            model = (ChatModel) registry.lookup("chatModel");
            view = new ChatViewImpl(model);
            ctrl = new ChatControllerImpl(model, view);
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
