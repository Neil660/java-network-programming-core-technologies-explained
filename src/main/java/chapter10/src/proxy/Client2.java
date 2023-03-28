package chapter10.src.proxy;

public class Client2 {
    public static void main(String args[]) {
        HelloService helloService = new HelloServiceImpl();
        HelloService helloServiceProxy =
                HelloServiceProxyFactory.getHelloServiceProxy(helloService);
        System.out.println("��̬�����������Ϊ"
                + helloServiceProxy.getClass().getName());
        System.out.println(helloServiceProxy.echo("Hello"));
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
