package chapter15.src;

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
        System.out.println(socket.getUseClientMode() ? "客户模式" : "服务器模式");
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
        String passphrase = "123456";
        char[] password = passphrase.toCharArray();

        String trustStoreFile = "test.keystore";
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream(trustStoreFile), password);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, tmf.getTrustManagers(), null);

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
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/
