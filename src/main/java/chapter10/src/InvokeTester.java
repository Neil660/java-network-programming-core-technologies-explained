package chapter10.src;

import java.lang.reflect.*;

public class InvokeTester {
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    public String echo(String msg) {
        return "echo:" + msg;
    }

    public static void main(String[] args) throws Exception {
        Class<InvokeTester> classType = InvokeTester.class;
        Object invokeTester = classType.getDeclaredConstructor().newInstance();

        //����InvokeTester�����add()����
        Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});
        Object result = addMethod.invoke(invokeTester, new Object[]{Integer.valueOf(100), Integer.valueOf(200)});
        System.out.println((Integer) result);

        //����InvokeTester�����echo()����
        Method echoMethod = classType.getMethod("echo", new Class[]{String.class});
        result = echoMethod.invoke(invokeTester, new Object[]{"Hello"});
        System.out.println((String) result);
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
