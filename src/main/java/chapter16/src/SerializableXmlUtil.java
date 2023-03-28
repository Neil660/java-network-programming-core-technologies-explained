package chapter16.src;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SerializableXmlUtil {
    /**
     * ��XML���ݷ����л�Ϊ JavaBean
     */
    public static <T> T parseXML(String xmlText) {
        XMLDecoder decoder = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(xmlText.getBytes());
            decoder = new XMLDecoder(new BufferedInputStream(in));
            return (T) decoder.readObject();
        } finally {
            decoder.close();
        }
    }

    /**
     * ��JavaBean���л�ΪXML����
     */
    public static <T> String formatXML(T entity) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(out));
        encoder.writeObject(entity);
        encoder.close();
        return out.toString();
    }

    public static void main(String[] args) throws Exception {
        Customer customer = new Customer(1, "Tom", "shanghai", 25);
        String xmlText = formatXML(customer);
        System.out.println("���л�ΪXML����:\n" + xmlText);
        customer = parseXML(xmlText);
        System.out.println("�����л�����JavaBean:\n" + customer);
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
