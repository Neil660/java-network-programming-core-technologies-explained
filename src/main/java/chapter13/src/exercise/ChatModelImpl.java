package chapter13.src.exercise;

import java.util.*;
import java.sql.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ChatModelImpl extends UnicastRemoteObject implements ChatModel {
    private Map<String, ChatView> clients = new HashMap<String, ChatView>();
    private static int count = 1;

    public ChatModelImpl() throws ChatException, RemoteException {
    }

    /**
     * ע��ͻ�����ͼ�����Իص���ͼ��ˢ�½���ķ���
     */
    public String registerClient(ChatView cv) throws RemoteException {
        String name = "Client" + count++;
        clients.put(name, cv);
        fireModelChangeEvent(toArray(clients.keySet()));
        return name;
    }

    /* ע��һ������ͻ� */
    public void unregisterClient(String clientName) throws RemoteException {
        clients.remove(clientName);
        fireModelChangeEvent(toArray(clients.keySet()));
    }

    /* �Ѽ���ת��ΪString[]����ΪString[]���Ա����л��������������ϴ��� */
    private String[] toArray(Set set) {
        String[] result = new String[set.size()];
        int i = 0;
        for (Object o : set)
            result[i++] = (String) o;

        return result;
    }

    /**
     * �����ݿ��пͻ���Ϣ�����仯ʱ��ͬ��ˢ�����е���ͼ
     */
    private void fireModelChangeEvent(Object o) {
        for (String name : clients.keySet()) {
            try {
                ChatView v = clients.get(name);
                v.handleInfoChange(o);
            } catch (Exception e) {
                try {
                    if (e instanceof ConnectException) //������������쳣����ע���ÿͻ�
                        unregisterClient(name);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                System.out.println(e.toString());
            }
        }
    }

    public String[] getClients() throws RemoteException {
        return toArray(clients.keySet());
    }

    public void transferMsg(String sendFrom, String sendTo, String msg) throws RemoteException {
        if (sendTo.equals("all"))
            fireModelChangeEvent(msg);
        else {
            ChatView from = clients.get(sendFrom);
            ChatView to = clients.get(sendTo);

            try {
                from.handleInfoChange(msg);
            } catch (Exception e) {
                if (e instanceof ConnectException) //������������쳣����ע���ÿͻ�
                    unregisterClient(sendFrom);
                System.out.println(e.toString());
            }

            try {
                if (!from.equals(to))
                    to.handleInfoChange(msg);
            } catch (Exception e) {
                if (e instanceof ConnectException) //������������쳣����ע���ÿͻ�
                    unregisterClient(sendTo);
                System.out.println(e.toString());
            }
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
