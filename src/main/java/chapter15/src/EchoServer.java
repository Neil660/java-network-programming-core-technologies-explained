package chapter15.src;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;

public class EchoServer {
    private int port = 8000;
    private SSLServerSocket serverSocket;

    public EchoServer() throws Exception {
        //输出跟踪日志
        //System.setProperty("javax.net.debug", "all");
        // SSLContext负责设置与安全通信有关的各种信息，比如使用的协议等
        SSLContext context = createSSLContext();
        SSLServerSocketFactory factory = context.getServerSocketFactory();
        serverSocket = (SSLServerSocket) factory.createServerSocket(port);
        System.out.println("服务器启动");
        System.out.println(serverSocket.getUseClientMode() ? "客户模式" : "服务器模式");
        System.out.println(serverSocket.getNeedClientAuth() ? "需要验证对方身份" : "不需要验证对方身份");

        // 返回当前对象所支持的加密套件组
        String[] supported = serverSocket.getSupportedCipherSuites();
        //设置当前对象可使用的加密套件组
        serverSocket.setEnabledCipherSuites(supported);
    }


    public SSLContext createSSLContext() throws Exception {
        //服务器用于证实自己身份的安全证书所在的密钥库
        String keyStoreFile = "test.keystore";
        String passphrase = "123456";
        //KeyStore用来存放包含安全证书的密钥库
        //JKS是JDK支持的KeyStore的类型
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] password = passphrase.toCharArray();
        //password参数用于打开密钥库
        ks.load(new FileInputStream(keyStoreFile), password);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        SSLContext sslContext = SSLContext.getInstance("SSL");
        //kmf.getKeyManagers()返回的对象用于选择用于证实自身身份的安全证书，把它发送给对方
        sslContext.init(kmf.getKeyManagers(), null, null);

        //当要求客户端提供安全证书时，服务器端可创建TrustManagerFactory，
        //并由它创建TrustManager，TrustManger根据与之关联的KeyStore中的信息，
        //来决定是否相信客户提供的安全证书。
        //String trustStoreFile = "test.keystore";
        //KeyStore ts = KeyStore.getInstance("JKS");
        //ts.load(new FileInputStream(trustStoreFile), password);
        //TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        //tmf.init(ts);
        //sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

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
                socket = serverSocket.accept();  //等待客户连接
                System.out.println("New connection accepted "
                        + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader br = getReader(socket);
                PrintWriter pw = getWriter(socket);

                String msg = null;
                while ((msg = br.readLine()) != null) {
                    System.out.println(msg);
                    pw.println(echo(msg));
                    if (msg.equals("bye")) //如果客户发送的消息为“bye”，就结束通信
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) socket.close();  //断开连接
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
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/

