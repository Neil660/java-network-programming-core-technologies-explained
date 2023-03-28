package chapter11.src.activate;

import java.util.Date;
import java.rmi.*;
import java.rmi.activation.*;
import java.io.*;

public class HelloServiceImpl extends Activatable implements HelloService {
    private String name;

    public HelloServiceImpl(ActivationID id, MarshalledObject<String> data) throws RemoteException {
        super(id, 0);
        try {
            this.name = data.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("����" + name);
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
