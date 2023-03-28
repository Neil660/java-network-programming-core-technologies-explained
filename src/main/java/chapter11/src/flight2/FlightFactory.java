package chapter11.src.flight2;

import java.rmi.*;

public interface FlightFactory extends Remote {
    public Flight getFlight(String flightNumber) throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
