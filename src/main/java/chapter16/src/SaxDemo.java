package chapter16.src;

import java.io.*;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxDemo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String xmlFile = "customer.xml"; //��������XML�ĵ�

        //����SAX������������
        SAXParserFactory factory = SAXParserFactory.newInstance();

        //ͨ��SAX������������SAX������
        SAXParser parser = factory.newSAXParser();

        CustomerHandler handler = new CustomerHandler();
        //��CustomerHandler�ĵ������� ����ָ����XML�ļ�
        parser.parse(new FileInputStream(xmlFile), handler);
        List<Customer> customers = handler.getCustomers();

        for (Customer c : customers) {
            System.out.println(c.getId() + "," + c.getName() + "," + c.getAge() + "," + c.getAddr());
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
