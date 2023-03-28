package chapter04.src.nonblock;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;

public class EchoServer {
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8000;
    private Charset charset = Charset.forName("GBK");

    public EchoServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        //ʹ����ͬһ�������Ϲر��˷��������򣬽������������÷���������ʱ��
        //����˳���󶨵���ͬ�Ķ˿�
        serverSocketChannel.socket().setReuseAddress(true);
        //����Ϊ������
        serverSocketChannel.configureBlocking(false);
        //�󶨶˿�
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("����������");
    }

    public void service() throws IOException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            //��ȡSelector��selected-keys����
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next();
                    it.remove();

                    if (key.isAcceptable()) { //����������Ӿ����¼�
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssc.accept();
                        System.out.println("���յ��ͻ����ӣ�����:" +
                                socketChannel.socket().getInetAddress() +
                                ":" + socketChannel.socket().getPort());
                        socketChannel.configureBlocking(false);
                        //����һ�����ڴ���û������������ݵĻ�����
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //Socketchannel��Selectorע��������¼���д�����¼�
                        socketChannel.register(
                                selector,
                                SelectionKey.OP_READ | SelectionKey.OP_WRITE,
                                buffer);
                    }
                    if (key.isReadable()) { //����������¼�
                        receive(key);
                    }
                    if (key.isWritable()) { //����д�����¼�
                        send(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (key != null) {
                            //ʹ��� SelectionKey ʧЧ�� .
                            //ʹ��Selector���ټ�����SelectionKey����Ȥ���¼�
                            key.cancel();
                            //�ر������ SelectionKey ������ Socketchannel
                            key.channel().close();
                        }
                    } catch (Exception ex) {
                        e.printStackTrace();
                    }
                }
            }//#while
        }//#while
    }

    public void send(SelectionKey key) throws IOException {
        //����� SelectionKey ������ ByteBuffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        //����� SelectionKey ������ SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.flip();  //�Ѽ�����Ϊλ�ã���λ����Ϊ0
        String data = decode(buffer);
        //�����û�ж���һ�����ݣ��ͷ���
        if (data.indexOf("\r\n") == -1) return;
        //��ȡһ������
        String outputData = data.substring(0, data.indexOf("\n") + 1);
        System.out.print(outputData);
        ByteBuffer outputBuffer = encode("echo:" + outputData);
        //���outputBuffer�е������ֽ�
        //hasRemaining ()�����ж��Ƿ���δ������ֽ�
        while (outputBuffer.hasRemaining())
            socketChannel.write(outputBuffer);

        ByteBuffer temp = encode(outputData);
        buffer.position(temp.limit());
        //ɾ��buffer���Ѿ����������
        buffer.compact();
        //�������������ַ���bye\r\n,��ʹSelectionKeyʧЧ�����ر�Socketchannel
        if (outputData.equals("bye\r\n")) {
            key.cancel();
            socketChannel.close();
            System.out.println("�ر���ͻ�������");
        }
    }

    public void receive(SelectionKey key) throws IOException {
        //�����SelectionKey�����ĸ���
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        //����� SelectionKey ������ SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        socketChannel.read(readBuff);
        readBuff.flip();

        buffer.limit(buffer.capacity());
        //��readBuff�е����ݿ�����buffer�У�
        //�ٶ�buffer�������㹻�󣬲�����ֻ���������쳣
        buffer.put(readBuff);
    }

    public String decode(ByteBuffer buffer) {  //����
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    public ByteBuffer encode(String str) {  //����
        return charset.encode(str);
    }

    public static void main(String args[]) throws Exception {
        EchoServer server = new EchoServer();
        server.service();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
