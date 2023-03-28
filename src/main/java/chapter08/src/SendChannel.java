package chapter08.src;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class SendChannel {
    public static void main(String args[]) throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        DatagramSocket socket = channel.socket();
        SocketAddress localAddr = new InetSocketAddress(7000);
        SocketAddress remoteAddr = new InetSocketAddress(InetAddress.getByName("localhost"), 8000);
        socket.bind(localAddr);
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            System.out.println("��������ʣ���ֽ�Ϊ" + buffer.remaining());
            int n = channel.send(buffer, remoteAddr);
            System.out.println("���͵��ֽ���Ϊ" + n);
            Thread.sleep(500);
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
