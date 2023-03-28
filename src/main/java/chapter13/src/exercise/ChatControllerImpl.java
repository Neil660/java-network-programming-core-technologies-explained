package chapter13.src.exercise;

import java.util.*;

public class ChatControllerImpl implements ChatController {
    private ChatModel chatModel;
    private ChatView chatView;

    public ChatControllerImpl(ChatModel model, ChatView view) {
        try {
            chatModel = model;
            chatView = view;
            view.addUserGestureListener(this); //����ͼע�����������
        } catch (Exception e) {
            reportException(e);
        }
    }

    /**
     * �����쳣��Ϣ
     */
    private void reportException(Object o) {
        try {
            chatView.showDisplay(o);
        } catch (Exception e) {
            System.out.println("ChatControllerImpl reportException" + e);
        }
    }

    /**
     * ��������Ϣ�Ķ���
     */
    public void handleSendMsgGesture(String from, String to, String msg) {
        try {
            chatModel.transferMsg(from, to, msg);
        } catch (Exception e) {
            reportException(e);
        }
    }

}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
