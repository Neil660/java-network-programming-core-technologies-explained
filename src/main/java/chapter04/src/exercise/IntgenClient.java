package chapter04.src.exercise;

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

public class IntgenClient {

    public static int DEFAULT_PORT = 8000;

    public static void main(String[] args) {
        String host = "localhost";
        int port = DEFAULT_PORT;

        try {
            SocketAddress address = new InetSocketAddress(host, port);
            SocketChannel client = SocketChannel.open(address);
            ByteBuffer buffer = ByteBuffer.allocate(4);
            IntBuffer view = buffer.asIntBuffer();

            for (int expected = 0; ; expected++) {
                client.read(buffer);
                int actual = view.get();
                buffer.clear();
                view.rewind();

                //�ж�ʵ���յ���int������Ԥ��Ӧ���յ��������Ƿ�һ��
                if (actual != expected) {
                    System.err.println("Ԥ��Ӧ���յ������ݣ� " + expected + "; ʵ���յ������ݣ� " + actual);
                    break;
                }
                System.out.println(actual);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/