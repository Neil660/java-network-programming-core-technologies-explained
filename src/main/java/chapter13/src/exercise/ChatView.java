package chapter13.src.exercise;

import java.rmi.*;

public interface ChatView extends Remote {
    /**
     * ע�ᴦ���û������ļ���������ChatController
     */
    public void addUserGestureListener(ChatController ctrl) throws ChatException, RemoteException;

    /**
     * ��ͼ�ν�������ʾ���ݣ� ����display��ʾ����ʾ������
     */
    public void showDisplay(Object display) throws ChatException, RemoteException;

    /**
     * ���пͻ�����������Ϣ�����пͻ���ע�ᣬͬ��ˢ����ؿͻ���ͼ�ν���
     */
    public void handleInfoChange(Object o) throws ChatException, RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
