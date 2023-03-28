package chapter02.src.auth;

import java.net.*;
import java.io.*;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.net.ssl.SSLSocketFactory;

public class MailSenderWithAuth {
    private String smtpServer = "smtp.126.com";  //SMTP�ʼ���������������
    private int port = 465;

    public static void main(String[] args) {
        Message msg = new Message("java_mailtest@126.com",   //�����ߵ��ʼ���ַ
                "weiqin_ok@aliyun.com",  //�����ߵ��ʼ���ַ
                "hello",  //�ʼ�����
                "hi,I miss you very much."); //�ʼ�����
        new MailSenderWithAuth().sendMail(msg);
    }


    public void sendMail(Message msg) {
        Socket socket = null;
        try {
            // socket = new Socket(smtpServer,port);  //���ӵ��ʼ�������
            socket = SSLSocketFactory.getDefault().createSocket(smtpServer, port);
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            String localhost = InetAddress.getLocalHost().getHostName();   //�ͻ�����������

            String username = "java_mailtest";
            String password = "access1234";  //��Ȩ��֤��
            //���û����Ϳ������base64����
            Base64.Encoder encoder = Base64.getEncoder();
            username = encoder.encodeToString(username.getBytes());
            password = encoder.encodeToString(password.getBytes()); //��Ȩ��֤��
            sendAndReceive(null, br, pw); //������Ϊ�˽��շ���������Ӧ����
            sendAndReceive("EHLO " + localhost, br, pw);
            sendAndReceive("AUTH LOGIN", br, pw);  //��֤����
            sendAndReceive(username, br, pw);  //�û���
            sendAndReceive(password, br, pw);   //��Ȩ��֤��
            sendAndReceive("MAIL FROM: " + msg.from + "", br, pw);

            sendAndReceive("RCPT TO: " + msg.to + "", br, pw);
            sendAndReceive("DATA", br, pw);  //��������ʼ�����ʼ�����
            pw.println(msg.data);  //�����ʼ�����
            System.out.println("Client>" + msg.data);
            sendAndReceive(".", br, pw);  //�ʼ��������
            sendAndReceive("QUIT", br, pw);  //����ͨ��
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ����һ���ַ�����������һ�з���������Ӧ����
     */
    private void sendAndReceive(String str, BufferedReader br, PrintWriter pw) throws IOException {
        if (str != null) {
            System.out.println("Client>" + str);
            pw.println(str);  //������str�ַ����󣬻��ᷢ�͡�\r\n����
        }
        String response;
        if ((response = br.readLine()) != null)
            System.out.println("Server>" + response);
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
}

class Message {  //��ʾ�ʼ�
    String from;  //�����ߵ��ʼ���ַ
    String to;  //�����ߵ��ʼ���ַ
    String subject;  //�ʼ�����
    String content;  //�ʼ�����
    String data;  //�ʼ����ݣ������ʼ����������

    public Message(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        data = "Subject:" + subject + "\r\n" + content;
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
