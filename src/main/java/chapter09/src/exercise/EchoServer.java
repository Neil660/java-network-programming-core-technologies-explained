package chapter09.src.exercise;

import java.io.*;
import java.net.*;

public class EchoServer {
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("����������");
    }

    public String echo(Object msg) {
        return "echo:" + msg;
    }

    private ObjectOutputStream getOutputStream(Socket socket) throws IOException {

        OutputStream socketOut = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(socketOut);
        return oos;
    }

    private ObjectInputStream getInputStream(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(socketIn);
        return ois;
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept(); // �ȴ��ͻ�����
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

                ObjectOutputStream oos = getOutputStream(socket);
                ObjectInputStream ois = getInputStream(socket);

                Object msg = null;
                while ((msg = ois.readObject()) != null) {
                    System.out.println("receive msg :" + msg);
                    oos.writeObject(echo(msg));

                    if (msg.equals("bye")) { // ����ͻ����͵���ϢΪ��bye�����ͽ���ͨ��
                        System.out.println("Finish talking. Bye!");
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null)
                        socket.close(); // �Ͽ�����
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new EchoServer().service();
    }
}


/****************************************************
 * ���ߣ���ΰ                                                                 *
 * ��Դ��<<Java�����̺��ļ������>>                   *
 * ����֧����ַ��www.javathinker.net                        *
 ***************************************************/
