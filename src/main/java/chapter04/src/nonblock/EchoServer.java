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
        //使得在同一个主机上关闭了服务器程序，紧接着再启动该服务器程序时，
        //可以顺利绑定到相同的端口
        serverSocketChannel.socket().setReuseAddress(true);
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("服务器启动");
    }

    public void service() throws IOException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            //获取Selector的selected-keys集合
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = null;
                try {
                    key = (SelectionKey) it.next();
                    it.remove();

                    if (key.isAcceptable()) { //处理接收连接就绪事件
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssc.accept();
                        System.out.println("接收到客户连接，来自:" +
                                socketChannel.socket().getInetAddress() +
                                ":" + socketChannel.socket().getPort());
                        socketChannel.configureBlocking(false);
                        //创建一个用于存放用户发送来的数据的缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //Socketchannel向Selector注册读就绪事件和写就绪事件
                        socketChannel.register(
                                selector,
                                SelectionKey.OP_READ | SelectionKey.OP_WRITE,
                                buffer);
                    }
                    if (key.isReadable()) { //处理读就绪事件
                        receive(key);
                    }
                    if (key.isWritable()) { //处理写就绪事件
                        send(key);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (key != null) {
                            //使这个 SelectionKey 失效， .
                            //使得Selector不再监控这个SelectionKey感兴趣的事件
                            key.cancel();
                            //关闭与这个 SelectionKey 关联的 Socketchannel
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
        //获得与 SelectionKey 关联的 ByteBuffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        //获得与 SelectionKey 关联的 SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.flip();  //把极限设为位置，把位置设为0
        String data = decode(buffer);
        //如果还没有读到一行数据，就返回
        if (data.indexOf("\r\n") == -1) return;
        //截取一行数据
        String outputData = data.substring(0, data.indexOf("\n") + 1);
        System.out.print(outputData);
        ByteBuffer outputBuffer = encode("echo:" + outputData);
        //输出outputBuffer中的所有字节
        //hasRemaining ()方法判断是否还有未处理的字节
        while (outputBuffer.hasRemaining())
            socketChannel.write(outputBuffer);

        ByteBuffer temp = encode(outputData);
        buffer.position(temp.limit());
        //删除buffer中已经处理的数据
        buffer.compact();
        //如果己经输出了字符串bye\r\n,就使SelectionKey失效，并关闭Socketchannel
        if (outputData.equals("bye\r\n")) {
            key.cancel();
            socketChannel.close();
            System.out.println("关闭与客户的连接");
        }
    }

    public void receive(SelectionKey key) throws IOException {
        //获得与SelectionKey关联的附件
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        //获得与 SelectionKey 关联的 SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuff = ByteBuffer.allocate(32);
        socketChannel.read(readBuff);
        readBuff.flip();

        buffer.limit(buffer.capacity());
        //把readBuff中的内容拷贝到buffer中，
        //假定buffer的容量足够大，不会出现缓冲区溢出异常
        buffer.put(readBuff);
    }

    public String decode(ByteBuffer buffer) {  //解码
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

    public ByteBuffer encode(String str) {  //编码
        return charset.encode(str);
    }

    public static void main(String args[]) throws Exception {
        EchoServer server = new EchoServer();
        server.service();
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/
