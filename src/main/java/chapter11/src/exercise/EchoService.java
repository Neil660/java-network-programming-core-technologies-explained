package chapter11.src.exercise;

import java.rmi.*;

public interface EchoService extends Remote {
    public String echo(String msg) throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
