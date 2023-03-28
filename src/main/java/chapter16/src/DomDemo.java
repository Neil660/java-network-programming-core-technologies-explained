package chapter16.src;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDemo {
    public static void parseXML(String xmlFile, String rootElementName) {
        //������������������ DocumentBuildFactory����
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document dom = null;
        try {
            //�ɽ������������󴴽����������󣬼�DocumentBuilder����
            db = dbf.newDocumentBuilder();

            //�ɽ����������ָ��XML�ļ����н���������Document����
            dom = db.parse(xmlFile);
     
      /* ��Document�����ʾ��DOM���н��в�ѯ��
         ������rootElementNameΪԪ����������Ԫ�ؽڵ���б�NoteList */
            NodeList nodelist = dom.getElementsByTagName(rootElementName);
            // �������еĽڵ�
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node node = nodelist.item(i);
                if (node instanceof Element)
                    walkThroughTree((Element) node);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("��������");
        }
    }

    /**
     * �ݹ�����Բ���elementΪ��Ԫ�صķ�֧��
     */
    public static void walkThroughTree(Element element) {
        String elementName = element.getTagName();
        String elementContent = element.getTextContent();
        System.out.println("Ԫ������" + elementName + ",Ԫ�����ݣ�" + elementContent);
        // �����ӽڵ�
        NodeList childList = element.getChildNodes();
        for (int j = 0; j < childList.getLength(); j++) {
            Node childNode = childList.item(j);
            if (childNode instanceof Element)
                walkThroughTree((Element) childNode);
        }
    }

    public static void main(String[] agrs) {
        String xmlFile = "mail.xml"; //��������XML�ļ�
        String rootElementName = "Mail"; //mail.xml�и�Ԫ�ص�����
        parseXML(xmlFile, rootElementName);
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
