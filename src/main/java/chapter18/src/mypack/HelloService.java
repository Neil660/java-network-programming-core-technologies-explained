package chapter18.src.mypack;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloService {
    @WebMethod
    public String sayHello(String username);
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
