package chapter11.src.hello;

import java.util.Date;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
    private String name;

    public HelloServiceImpl(String name) throws RemoteException {
        this.name = name;
    }

    public String echo(String msg) throws RemoteException {
        System.out.println(name + ":����echo()����");
        return "echo:" + msg + " from " + name;
    }

    public Date getTime() throws RemoteException {
        System.out.println(name + ":����getTime()����");
        return new Date();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
