package chapter15.src.exercise;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;

public class EchoClient {
    private String host = "localhost";
    private int port = 8000;
    private SSLSocket socket;

    public EchoClient() throws Exception {
        SSLContext context = createSSLContext();
        SSLSocketFactory factory = context.getSocketFactory();
        socket = (SSLSocket) factory.createSocket(host, port);
        String[] supported = socket.getSupportedCipherSuites();
        socket.setEnabledCipherSuites(supported);
        System.out.println(socket.getUseClientMode() ? "�ͻ�ģʽ" : "������ģʽ");
    }

    public static void main(String args[]) throws Exception {
        new EchoClient().talk();
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public SSLContext createSSLContext() throws Exception {
        String keyStoreFile = "client.keystore";
        String passphrase = "123456";
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] password = passphrase.toCharArray();
        ks.load(new FileInputStream(keyStoreFile), password);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        //��Ҫ��ͻ����ṩ��ȫ֤��ʱ���������˿ɴ���TrustManagerFactory��
        //����������TrustManager��TrustManger������֮������KeyStore�е���Ϣ��
        //�������Ƿ����ſͻ��ṩ�İ�ȫ֤�顣
        String trustStoreFile = "clientTrust.keystore";
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream(trustStoreFile), password);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        return sslContext;
    }

    public void talk() throws IOException {
        try {
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg = localReader.readLine()) != null) {

                pw.println(msg);
                pw.flush();
                System.out.println(br.readLine());

                if (msg.equals("bye"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
