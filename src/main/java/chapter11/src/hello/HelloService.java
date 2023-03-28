package chapter11.src.hello;

import java.util.Date;
import java.rmi.*;

public interface HelloService extends Remote {
    public String echo(String msg) throws RemoteException;

    public Date getTime() throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
