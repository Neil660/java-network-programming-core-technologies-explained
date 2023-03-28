package chapter03.src.exercise;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class FileServer {
    private int port = 8000;
    private ServerSocket serverSocket;
    private ExecutorService executorService;  //�̳߳�
    private final int POOL_SIZE = 4;  //����CPUʱ�̳߳��й����̵߳���Ŀ

    public FileServer() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_SIZE);

        System.out.println("����������");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* ���ܿͻ��������ļ����� */
    private void getFile(OutputStream socketoustream, String[] data) throws IOException {

        DataOutputStream socketout = new DataOutputStream(socketoustream);
        String filename = data[1];
        java.io.File file = new java.io.File("server//" + filename);

        if (!file.exists()) {
            socketout.writeInt(0);// �ļ������ڣ�ֱ��д����Ϊ0
            return;
        } else {
            String msgString = "ok:" + file.length();
            byte[] msgbytes = msgString.getBytes();
            System.out.println("server :Server Response '" + msgString + "' .");

            socketout.writeInt(msgbytes.length);// д����Ϣ����
            socketout.write(msgbytes);// д����Ϣ����
        }

        //���������ļ�
        FileInputStream fInputStream = new FileInputStream("server//" + filename);

        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = fInputStream.read(buff)) != -1) {//��������ļ����͵��ͻ���
            socketout.write(buff, 0, len);
        }
        fInputStream.close();
        System.out.println(String.format("server :finish sending file %1s !", filename));
        System.out.println();

    }

    /* ���ܿͻ����ϴ��ļ����� */
    private void putFile(InputStream socketIn, String[] data) throws IOException {
        String filename = data[1];
        int length = Integer.parseInt(data[2]);
        String path = "server//" + filename;

        FileOutputStream fileOut = new FileOutputStream(path);
        byte[] buff = new byte[1024];

        int m = length / buff.length;
        int n = length % buff.length;
        int len = 0;
        for (int i = 0; i < m + 1; i++) {
            if (i == m) {
                len = socketIn.read(buff, 0, n);
                fileOut.write(buff, 0, len);
            } else {
                len = socketIn.read(buff);
                fileOut.write(buff, 0, len);
            }
        }
        fileOut.close();
        System.out.println(String.format("server :finish receiving file %1s !", filename));
        System.out.println();
    }

    /* �Կո�Ϊ��λ�ָ���Ϣ */
    private String[] splistparams(String msg) {
        String[] params = msg.split(" ");
        return params;
    }

    public static void main(String args[]) throws IOException {
        new FileServer().service();
    }


    class Handler implements Runnable {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                String clientAddress = socket.getInetAddress() + ":" + socket.getPort();
                System.out.println("server :New connection accepted " + clientAddress);

                DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
                int msglen = 0;
                ;
                String msg = null;
                do {
                    msglen = dInputStream.readInt();// ������Ϣ����

                    if (msglen == 0)
                        continue;// ����Ϣ����Ϊ 0�������κδ���

                    byte[] buffer = new byte[msglen];
                    dInputStream.read(buffer);
                    msg = new String(buffer); // ����ָʾ����Ϣ���ȣ���ȡ�ַ�������
                    if (msg != null) {
                        System.out.println("server :Client(" + clientAddress + ") Request is  " + msg);

                        if (msg.equals("bye")) {
                            break; // ����ͻ����͵���ϢΪ��bye�����ͽ�������ͨ��
                        }

                        String[] data = splistparams(msg);
                        String command = data[0];
                        switch (command) {
                            case "put":
                                putFile(socket.getInputStream(), data);//���ܿͻ����ϴ��ļ�����
                                break;

                            case "get":
                                getFile(socket.getOutputStream(), data);//���ܿͻ��������ļ�����

                                break;

                            default:
                                break;

                        }

                    }
                } while (msg != null);
            } catch (IOException e) {
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
}

/****************************************************
 * ���ߣ���ΰ                                                                 *
 * ��Դ��<<Java�����̺��ļ������>>                   *
 * ����֧����ַ��www.javathinker.net                        *
 ***************************************************/
