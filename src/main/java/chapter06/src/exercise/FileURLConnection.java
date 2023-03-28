package chapter06.src.exercise;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class FileURLConnection extends URLConnection {
    private Socket connection = null;
    public final static int DEFAULT_PORT = 8000;

    public FileURLConnection(URL url) {
        super(url);
    }

    public synchronized InputStream getInputStream() throws IOException {
        if (!connected) connect();
        return connection.getInputStream();
    }

    public synchronized OutputStream getOutputStream() throws IOException {
        if (!connected) connect();
        return connection.getOutputStream();
    }

    public String getContentType() {
        return "application/octet-stream";
    }

    public synchronized void connect() throws IOException {
        if (!connected) {
            int port = url.getPort();
            if (port < 0 || port > 65535) port = DEFAULT_PORT;
            this.connection = new Socket(url.getHost(), port);
            this.connected = true;
        }
    }

    public synchronized void disconnect() throws IOException {
        if (connected) {
            this.connection.close();
            this.connected = false;
        }
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程核心技术详解>>                       *
 * 技术支持网址：www.javathinker.net                *
 ***************************************************/
