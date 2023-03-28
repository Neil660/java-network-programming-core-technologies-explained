package chapter13.src.exercise;

import java.rmi.*;
import java.util.*;

public interface ChatModel extends Remote {
    /**
     * ע��һ������ͻ���Ϊ�˼򻯳��򣬿����ɸ÷���Ϊ�ͻ�����һ����ʱ��Ψһ�Ŀͻ�����
     * �����һ��ע��Ŀͻ�Ϊ��client1�����ڶ���ע��Ŀͻ�Ϊ��client2�����������ơ�
     */
    public String registerClient(ChatView client) throws RemoteException;

    /**
     * ע��һ������ͻ�
     */
    public void unregisterClient(String client) throws RemoteException;

    /**
     * ת����Ϣ������sendFrom��ʾ�����ߵĿͻ���������sendTo��ʾ�����ߵĿͻ���
     */
    public void transferMsg(String sendFrom, String sendTo, String msg) throws RemoteException;

    /**
     * �����������ͻ��Ŀͻ���
     */
    public String[] getClients() throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
