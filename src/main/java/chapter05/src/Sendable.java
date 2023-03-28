package chapter05.src;

import java.io.*;

/*
 *��ʾ���������Է��͸��ͻ��˵Ķ���
 */
public interface Sendable {
    // ׼�����͵�����
    public void prepare() throws IOException;

    // ����ͨ�����Ͳ������ݣ�����������ݷ�����ϣ��ͷ���false
    // �����������δ���ͣ��ͷ���true
    // ������ݻ�û��׼���ã����׳�IllegalStateException
    public boolean send(ChannelIO cio) throws IOException;

    //������������������ϣ��͵��ô˷������ͷ�����ռ�õ���Դ
    public void release() throws IOException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
