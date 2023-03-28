package chapter07.src;

import javax.swing.text.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.html.*;
import java.beans.*;

public class SimpleWebBrowser extends JFrame implements HyperlinkListener, ActionListener {
    private JTextField jtf = new JTextField(40);
    private JEditorPane jep = new JEditorPane();
    private String initialPage = "http://www.javathinker.net/helloapp/index.htm";

    public SimpleWebBrowser(String title) {
        super(title);

        jtf.setText(initialPage);
        jtf.addActionListener(this);
        jep.setEditable(false);
        jep.addHyperlinkListener(this);

        //����editorKit���Ա��������õ��¼�
        jep.addPropertyChangeListener("editorKit", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                EditorKit kit = jep.getEditorKit();
                if (kit.getClass() == HTMLEditorKit.class) {
                    ((HTMLEditorKit) kit).setAutoFormSubmission(false);
                }
            }
        });


        try {
            jep.setPage(initialPage);
        } catch (IOException e) {
            showError(initialPage);
        }

        JScrollPane scrollPane = new JScrollPane(jep);
        Container container = getContentPane();
        container.add(jtf, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void showError(String urlStr) {
        try {
            jep.setPage("http://www.javathinker.net/helloapp/error.htm");
            jtf.setText(urlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleWebBrowser browser = new SimpleWebBrowser("Simple Web Browser");
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            testURL(jtf.getText());
            jep.setPage(jtf.getText());
        } catch (Exception e) {
            showError(jtf.getText());
        }
    }

    /**
     * �����û�ѡ�񳬼����ӻ����ύ���¼�
     */
    public void hyperlinkUpdate(HyperlinkEvent evt) {
        try {
            testURL(evt.getURL().toString());

            if (evt.getClass() == FormSubmitEvent.class) {  //�����ύ���¼�
                FormSubmitEvent fevt = (FormSubmitEvent) evt;
                URL url = fevt.getURL(); //���URL
                String method = fevt.getMethod().toString(); //�������ʽ
                String data = fevt.getData(); //��ñ�����

                if (method.equals("GET")) {  //���ΪGET����ʽ
                    jep.setPage(url.toString() + "?" + data);
                    jtf.setText(url.toString() + "?" + data); //���ı�����Ϊ�û�ѡ��ĳ�������
                } else if (method.equals("POST")) {  //���ΪPOST����ʽ
                    URLConnection uc = url.openConnection();
                    //����HTTP��������
                    uc.setDoOutput(true);
                    OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream());
                    out.write(data);
                    out.close();
                    jep.setPage(url);
                    jtf.setText(url.toString()); //���ı�����Ϊ�û�ѡ��ĳ�������
                }
            } else if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                //�����û�ѡ��ĳ�������
                jep.setPage(evt.getURL());
                jtf.setText(evt.getURL().toString()); //���ı�����Ϊ�û�ѡ��ĳ�������
            }

        } catch (Exception e) {
            showError(evt.getURL().toString());
        }
    }

    /* �����Ƿ�Ϊ��Ч��URL */
    public void testURL(String url) throws Exception {
        try {
            System.out.println("Visiting URL:" + url);
            new URL(url).openStream().close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("��Ч��URL");
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
