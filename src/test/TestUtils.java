import org.junit.Test;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

public class TestUtils {
    @Test
    public void Test() throws Exception {
        String passphrase = "123456";
//JKS��JDK֧�ֵ�KeyStore������
        KeyStore keyStore = KeyStore.getInstance("JKS");
        char[] password = passphrase.toCharArray();
//password�������ڴ���Կ��
        keyStore.load(new FileInputStream("test.keystore"), password);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509n");
        kmf.init(keyStore, password);
        KeyManager[] keyManagers = kmf.getKeyManagers();


        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(keyStore);
        TrustManager[] trustManagers = tmf.getTrustManagers();

        //��� TLS Э��
        SSLContext sslCtx = SSLContext.getInstance("TLS");
        sslCtx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLServerSocketFactory ssf = sslCtx.getServerSocketFactory();


        //�����˿� 8000
        SSLServerSocket serverSocket= (SSLServerSocket) ssf.createServerSocket(8000);




    }
}
