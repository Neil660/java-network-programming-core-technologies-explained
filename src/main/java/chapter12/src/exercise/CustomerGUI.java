package chapter12.src.exercise;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomerGUI extends JFrame {

    private int currentlyPage = 1; //��ǰ��ҳ��
    private JPanel center, bottom;
    private JButton previously, next, first, tail;
    private JTextArea ta;
    private DBService dbService = new DBService();
    ArrayList<Customer> arrayCustomers = null;

    public CustomerGUI(String title) {
        super(title);
        this.previously = new JButton("��һҳ");
        this.next = new JButton("��һҳ");
        this.first = new JButton("��ҳ");
        this.tail = new JButton("βҳ");
        this.ta = new JTextArea();
        ta.setEditable(false);
        ta.setRows(5);
        ta.setBounds(0, 0, 40, 20);
        this.initialization();

        this.center = new JPanel(new GridLayout(1, 1));
        this.bottom = new JPanel(new GridLayout(1, 4));

        center.add(ta);

        bottom.add(previously);
        bottom.add(next);
        bottom.add(first);
        bottom.add(tail);

        /*����ҳ����һҳ����һҳ��βҳ������*/
        MyListener ml = new MyListener();
        this.previously.addActionListener(ml);
        this.next.addActionListener(ml);
        this.first.addActionListener(ml);
        this.tail.addActionListener(ml);

        this.getContentPane().add(center, BorderLayout.CENTER);
        this.getContentPane().add(bottom, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBounds(200, 200, 350, 180);
        this.setVisible(true);
    }

    /**
     * ��ʼ����һҳ
     */
    void initialization() {
        showData();
    }

    /**
     * ����ť�ķ�ҳ
     */
    class MyListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ta.setText("");
            if (e.getSource() == previously) {//��һҳ�İ�ť
                if (currentlyPage >= 2) {
                    currentlyPage--;
                } else {
                    ta.append("��ǰΪ��һҳ��\n");
                }
                //չʾ����
                showData();


            } else if (e.getSource() == next) { //���Ϊ��һҳ
                ta.setText("");
                int pagesNum = dbService.getPagesNum();
                if (currentlyPage < pagesNum) {//�����ǰ��ҳ��С����ҳ��
                    currentlyPage++;
                } else {
                    ta.append("��ǰΪ���һҳ��\n");
                }

                showData();

            } else if (e.getSource() == first) {//�������ҳ
                ta.setText("");
                currentlyPage = 1;
                showData();


            } else if (e.getSource() == tail) {//�����βҳ
                ta.setText("");
                currentlyPage = dbService.getPagesNum();
                showData();
            }
        }
    }

    /**
     * չʾ�ض�ҳ�������Customer���������
     */
    public void showData() {
        arrayCustomers = dbService.getPerPageData(currentlyPage);
        Iterator<Customer> i = arrayCustomers.iterator();
        while (i.hasNext()) {
            Customer c = (Customer) i.next();
            ta.append(c.toString());
        }
    }

    public static void main(String[] args) {
        CustomerGUI gui = new CustomerGUI("�ͻ���Ϣ");
    }
}