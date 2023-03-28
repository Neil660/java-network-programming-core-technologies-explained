package chapter13.src.store;

import java.rmi.*;

public interface StoreView extends Remote {
    /**
     * ע�ᴦ���û������ļ���������StoreController
     */
    public void addUserGestureListener(StoreController ctrl) throws StoreException, RemoteException;

    /**
     * ��ͼ�ν�������ʾ���ݣ� ����display��ʾ����ʾ������
     */
    public void showDisplay(Object display) throws StoreException, RemoteException;

    /**
     * �����������޸������ݿ���ĳ���ͻ�����Ϣ��ͬ��ˢ�����пͻ������е�ͼ�ν���
     */
    public void handleCustomerChange(Customer cust) throws StoreException, RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
