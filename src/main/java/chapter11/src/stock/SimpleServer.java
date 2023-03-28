package chapter11.src.stock;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class SimpleServer {
    public static void main(String args[]) {
        try {
            StockQuoteRegistryImpl quoteRegistry = new StockQuoteRegistryImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("StockQuoteRegistry", quoteRegistry);
            System.out.println("������ע����һ��StockQuoteRegistry����");

            new Thread(quoteRegistry).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
