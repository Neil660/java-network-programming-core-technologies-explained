package chapter05.src.exercise;

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
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("����������");
    }

    public void service() throws IOException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = (SocketChannel) ssc.accept();
                        System.out.println("���յ��ͻ����ӣ�����:" +
                                socketChannel.socket().getInetAddress() +
                                ":" + socketChannel.socket().getPort());
                        socketChannel.configureBlocking(false);
                        ChannelIO channelIO = new ChannelIO(socketChannel, false);

                        //��ChannelIO��Ϊ������Selector����
                        socketChannel.register(selector,
                                SelectionKey.OP_READ |
                                        SelectionKey.OP_WRITE, channelIO);
                    }
                    if (key.isReadable()) {
                        receive(key);
                    }
                    if (key.isWritable()) {
                        send(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (key != null) {
                            key.cancel();
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
        ChannelIO channelIO = (ChannelIO) key.attachment();
        ByteBuffer buffer = channelIO.getReadBuf();
        buffer.flip();  //�Ѽ�����Ϊλ�ã���λ����Ϊ0
        String data = decode(buffer);
        if (data.indexOf("\r\n") == -1) return;
        String outputData = data.substring(0, data.indexOf("\n") + 1);
        System.out.print(outputData);
        ByteBuffer outputBuffer = encode("echo:" + outputData);
        while (outputBuffer.hasRemaining())
            channelIO.write(outputBuffer);

        ByteBuffer temp = encode(outputData);
        buffer.position(temp.limit());
        buffer.compact();

        if (outputData.equals("bye\r\n")) {
            key.cancel();
            channelIO.close();
            System.out.println("�ر���ͻ�������");
        }
    }

    public void receive(SelectionKey key) throws IOException {
        ChannelIO channelIO = (ChannelIO) key.attachment();
        channelIO.read();
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
