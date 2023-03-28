package chapter11.src.sync;

import java.rmi.*;

public interface Stack extends Remote {
    public String getName() throws RemoteException;

    public int getPoint() throws RemoteException;

    public String pop() throws RemoteException;

    public void push(String goods) throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
