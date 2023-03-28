package chapter11.src.stock;

import java.rmi.*;

public interface StockQuoteRegistry extends Remote {
    public void registerClient(StockQuote client) throws RemoteException;

    public void unregisterClient(StockQuote client) throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
