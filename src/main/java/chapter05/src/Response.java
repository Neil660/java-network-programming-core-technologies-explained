package chapter05.src;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;

public class Response implements Sendable {
    static enum Code {  //ö���࣬��ʾ״̬����
        OK(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found"),
        METHOD_NOT_ALLOWED(405, "Method Not Allowed");

        private int number;
        private String reason;

        private Code(int i, String r) {
            number = i;
            reason = r;
        }

        public String toString() {
            return number + " " + reason;
        }
    }

    private Code code;  //״̬����
    private Content content;  //��Ӧ����
    private boolean headersOnly;  //��ʾHTTP��Ӧ���Ƿ��������Ӧͷ
    private ByteBuffer headerBuffer = null;  //��Ӧͷ

    public Response(Code rc, Content c) {
        this(rc, c, null);
    }

    public Response(Code rc, Content c, Request.Action head) {
        code = rc;
        content = c;
        headersOnly = (head == Request.Action.HEAD);
    }

    private static String CRLF = "\r\n";
    private static Charset responseCharset = Charset.forName("GBK");

    /* ������Ӧͷ�����ݣ�������ŵ�һ��ByteBuffer�� */
    private ByteBuffer headers() {
        CharBuffer cb = CharBuffer.allocate(1024);
        for (; ; ) {
            try {
                cb.put("HTTP/1.1 ").put(code.toString()).put(CRLF);
                cb.put("Server: nio/1.1").put(CRLF);
                cb.put("Content-type: ").put(content.type()).put(CRLF);
                cb.put("Content-length: ")
                        .put(Long.toString(content.length())).put(CRLF);
                cb.put(CRLF);
                break;
            } catch (BufferOverflowException x) {
                assert (cb.capacity() < (1 << 16));
                cb = CharBuffer.allocate(cb.capacity() * 2);
                continue;
            }
        }
        cb.flip();
        return responseCharset.encode(cb);  //����
    }

    /* ׼��HTTP��Ӧ�е������Լ���Ӧͷ������ */
    public void prepare() throws IOException {
        content.prepare();
        headerBuffer = headers();
    }

    /* ����HTTP��Ӧ�����ȫ��������ϣ�����false�����򷵻�true */
    public boolean send(ChannelIO cio) throws IOException {
        if (headerBuffer == null)
            throw new IllegalStateException();

        //������Ӧͷ
        if (headerBuffer.hasRemaining()) {
            if (cio.write(headerBuffer) <= 0)
                return true;
        }

        //������Ӧ����
        if (!headersOnly) {
            if (content.send(cio))
                return true;
        }

        return false;
    }

    /* �ͷ���Ӧ����ռ�õ���Դ */
    public void release() throws IOException {
        content.release();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
