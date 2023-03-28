package chapter10.src.proxy;

import java.lang.reflect.*;

public class HelloServiceProxyFactory {
    public static HelloService getHelloServiceProxy(final HelloService helloService) {
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object args[]) throws Exception {
                System.out.println("before calling " + method);
                Object result = method.invoke(helloService, args);
                System.out.println("after calling " + method);
                return result;
            }
        };

        Class<HelloService> classType = HelloService.class;
        return (HelloService) Proxy.newProxyInstance(classType.getClassLoader(),
                new Class[]{classType},
                handler);

    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
