package chapter11.src.stock;

import java.rmi.*;

/**
 * �ͻ��˵�Զ�̶���ӿ�
 */
public interface StockQuote extends Remote {
    public void quote(String stockSymbol, double value) throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
