package chapter10.src;

import java.lang.reflect.*;

public class DumpMethods {
    public static void main(String args[]) throws Exception {
        // ���ز���ʼ�������в���ָ������
        Class<?> classType = Class.forName(args[0]);
        //���������з���
        Method methods[] = classType.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
            System.out.println(methods[i].toString());
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
