package chapter05.src;

/**
 * ��HTTP�����޷���ȷ����ʱ���׳����쳣
 */
public class MalformedRequestException extends Exception {

    MalformedRequestException() {
    }

    MalformedRequestException(String msg) {
        super(msg);
    }

    MalformedRequestException(Exception x) {
        super(x);
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
