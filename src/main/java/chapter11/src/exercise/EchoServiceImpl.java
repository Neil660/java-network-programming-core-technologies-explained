package chapter11.src.exercise;

import java.util.Date;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class EchoServiceImpl extends UnicastRemoteObject implements EchoService {
    public EchoServiceImpl() throws RemoteException {
    }

    public String echo(String msg) throws RemoteException {
        return "echo:" + msg;
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
