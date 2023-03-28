package chapter15.src.exercise;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;

public class EchoServer {
    private int port = 8000;
    private SSLServerSocket serverSocket;

    public EchoServer() throws Exception {
        //���������־
        //System.setProperty("javax.net.debug", "all");
        SSLContext context = createSSLContext();
        SSLServerSocketFactory factory = context.getServerSocketFactory();
        serverSocket = (SSLServerSocket) factory.createServerSocket(port);
        System.out.println("����������");
        System.out.println(serverSocket.getUseClientMode() ? "�ͻ�ģʽ" : "������ģʽ");
        serverSocket.setNeedClientAuth(true);
        System.out.println(serverSocket.getNeedClientAuth() ? "��Ҫ��֤�Է����" : "����Ҫ��֤�Է����");

        String[] supported = serverSocket.getSupportedCipherSuites();
        serverSocket.setEnabledCipherSuites(supported);
    }


    public SSLContext createSSLContext() throws Exception {
        String keyStoreFile = "server.keystore";
        String passphrase = "123456";
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] password = passphrase.toCharArray();
        ks.load(new FileInputStream(keyStoreFile), password);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        //��Ҫ��ͻ����ṩ��ȫ֤��ʱ���������˿ɴ���TrustManagerFactory��
        //����������TrustManager��TrustManger������֮������KeyStore�е���Ϣ��
        //�������Ƿ����ſͻ��ṩ�İ�ȫ֤�顣
        String trustStoreFile = "serverTrust.keystore";
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream(trustStoreFile), password);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        return sslContext;
    }


    public String echo(String msg) {
        return "echo:" + msg;
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();  //�ȴ��ͻ�����
                System.out.println("New connection accepted "
                        + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader br = getReader(socket);
                PrintWriter pw = getWriter(socket);

                String msg = null;
                while ((msg = br.readLine()) != null) {
                    System.out.println(msg);
                    pw.println(echo(msg));
                    pw.flush();
                    if (msg.equals("bye")) //����ͻ����͵���ϢΪ��bye�����ͽ���ͨ��
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) socket.close();  //�Ͽ�����
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) throws Exception {
        new EchoServer().service();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
