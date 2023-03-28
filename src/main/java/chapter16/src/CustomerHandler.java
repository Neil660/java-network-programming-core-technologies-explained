package chapter16.src;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CustomerHandler extends DefaultHandler {
    private List<Customer> customers;
    private Customer customer;  //��ǰ���ڴ����Customer����
    private String tag;  //��ǰ���ڴ����Ԫ����

    /**
     * ��ʼ�����ĵ�ʱ���ô˷���
     */
    public void startDocument() throws SAXException {
        System.out.println("�ĵ�������ʼ");
        customers = new ArrayList<Customer>();
    }

    /**
     * ��ʼ����һ��Ԫ��ʱ���ô˷���
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("��ʼ����һ��Ԫ��" + qName);
        if (null != qName) {
            tag = qName;
        }
        if (null != qName && qName.equals("customer")) {
            customer = new Customer();
        }
    }

    /**
     * �������Ԫ�ص��ı����� �������Ǳ���ΪCustomer���������
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String str = new String(ch, start, length);
        if (null != tag && tag.equals("name")) {
            customer.setName(str);
        } else if (null != tag && tag.equals("age")) {
            Integer age = Integer.valueOf(str);
            customer.setAge(age);
        } else if (null != tag && tag.equals("address")) {
            customer.setAddr(str);
        } else if (null != tag && tag.equals("id")) {
            Long id = Long.valueOf(str);
            customer.setId(id);
        }
    }

    /**
     * ����Ԫ�ؽ�βʱ���ô˷���
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("��������һ��Ԫ��" + qName);
        if (qName.equals("customer")) {
            this.customers.add(customer);
        }
        tag = null;
    }

    /**
     * �����ĵ�����ʱ���ô˷���
     */
    public void endDocument() throws SAXException {
        System.out.println("�ĵ���������");
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
