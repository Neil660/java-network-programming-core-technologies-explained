package chapter11.src.dgc;

import java.util.Date;
import java.rmi.*;

public interface HelloService extends Remote {
    public boolean isAccessed() throws RemoteException;

    public void access() throws RemoteException;

    public void bye() throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
