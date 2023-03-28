package chapter08.src.exercise;

import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel {
    Image image = null;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
