package chapter13.src.exercise;

import java.util.*;
import java.io.Serializable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;
import java.rmi.server.*;

public class ChatViewImpl extends UnicastRemoteObject
        implements ChatView, Serializable {
    private transient ChatGui gui;
    private ChatModel chatmodel;
    private Object display;
    private String name;
    private ArrayList<ChatController> chatControllers = new ArrayList<ChatController>(10);

    public ChatViewImpl(ChatModel model) throws RemoteException {
        try {
            chatmodel = model;
            name = model.registerClient(this);
        } catch (Exception e) {
            System.out.println("ChatViewImpl constructor " + e);
        }

        gui = new ChatGui(name);
        gui.refreshAllClients(model.getClients());
        //��ͼ�ν���ע�������
        gui.addSendButtonListener(sendButtonHandler);
    }

    /**
     * ���������
     */
    public void addUserGestureListener(ChatController ctr) throws ChatException, RemoteException {
        chatControllers.add(ctr);
    }

    /**
     * ��ͼ�ν�����չʾ����displayָ��������
     */
    public void showDisplay(Object display) throws ChatException, RemoteException {
        if (!(display instanceof Exception)) this.display = display;

        if (display instanceof String) {
            gui.refreshChatMsg((String) display);
        }

        if (display instanceof String[]) {
            gui.refreshAllClients((String[]) display);
        }

        if (display instanceof Exception) {
            gui.refreshChatMsg(((Exception) display).getMessage());
        }

    }

    /**
     * ˢ�½����ϵ���Ϣ
     */
    public void handleInfoChange(Object obj) throws ChatException, RemoteException {
        if (gui == null) return;
        try {
            if (obj instanceof String[]) {  //ˢ�¿ͻ��б�
                gui.refreshAllClients((String[]) obj);
            } else if (obj instanceof String) {  //ˢ��������Ϣ
                gui.refreshChatMsg((String) obj);
            }
        } catch (Exception e) {
            System.out.println("ChatViewImpl:" + e);
        }
    }


    /**
     * ����ͼ�ν����Ϸ��Ͱ�ť��ActionEvent�ļ�����
     */
    transient ActionListener sendButtonHandler = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ChatController ctr;
            for (int i = 0; i < chatControllers.size(); i++) {
                ctr = chatControllers.get(i);
                ctr.handleSendMsgGesture(name,
                        gui.getToClient(),
                        name + ":" + gui.getMessage());
            }

        }
    };

}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
