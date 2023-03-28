package chapter04.src.gui;

import javax.swing.text.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.*;
import java.util.List;

public class EchoClient extends JFrame implements ActionListener {
    private JLabel clientLabel = new JLabel("�ͻ����������ݣ�");
    private JTextField clientTextField = new JTextField();
    private JLabel serverLabel = new JLabel("���������ص���Ӧ���");
    private JTextPane serverTextPane = new JTextPane();

    private SocketChannel socketChannel = null;

    public EchoClient(String title) {
        super(title);

        clientTextField.addActionListener(this);
        serverTextPane.setEditable(false);

        JScrollPane serverScrollPane = new JScrollPane(serverTextPane);

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new BorderLayout());
        clientPanel.add(clientLabel, BorderLayout.NORTH);
        clientPanel.add(clientTextField, BorderLayout.SOUTH);

        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new BorderLayout());
        serverPanel.add(serverLabel, BorderLayout.NORTH);
        serverPanel.add(serverScrollPane, BorderLayout.CENTER);

        Container container = getContentPane();

        container.add(clientPanel, BorderLayout.NORTH);
        container.add(serverPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setVisible(true);

        connect();
    }

    public void connect() {
        try {
            socketChannel = SocketChannel.open();
            InetAddress ia = InetAddress.getLocalHost();
            InetSocketAddress isa = new InetSocketAddress(ia, 8000);
            socketChannel.connect(isa);
            setServerTextPane("������������ӽ����ɹ�");
        } catch (Exception e) {
            setServerTextPane("�����������ʧ��");
        }
    }

    public void setServerTextPane(String text) {
        serverTextPane.setText(serverTextPane.getText() + "\r\n" + text);
    }

    public static void main(String[] args) {
        //��EDT�߳��ύ����EchoClient����
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EchoClient echoClient = new EchoClient("EchoClient");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public String talk() throws IOException {
        BufferedReader br = getReader(socketChannel.socket());
        PrintWriter pw = getWriter(socketChannel.socket());
        String msg = clientTextField.getText();
        pw.println(msg);
        return br.readLine();
    }

    /* �����û����ı����лس����¼� */
    public void actionPerformed(ActionEvent evt) {
        try {
            setServerTextPane(talk());
        } catch (Exception e) {
            setServerTextPane(e.getMessage());
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
