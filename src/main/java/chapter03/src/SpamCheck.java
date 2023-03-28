package chapter03.src;

import java.net.*;
import java.util.concurrent.*;
import java.util.*;
import java.io.*;

public class SpamCheck {
    private static final int POOL_SIZE = 4;  //����CPUʱ�̳߳��й����̵߳���Ŀ

    public static void main(String[] args) throws Exception {
        ExecutorService executorService;  //�̳߳�

        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_SIZE);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(
                new FileInputStream("address.txt")));
        String addr = null;
        Set<Future<String>> futureResults = new HashSet<Future<String>>();
        while ((addr = fileReader.readLine()) != null) {
            LookupTask task = new LookupTask(addr);
            Future<String> future = executorService.submit(task);
            futureResults.add(future);
        }

/*
     for(Future<String> result:futureResults){
        //�������û��ɣ�get()������������ֱ��������ɣ����ؽ����
        System.out.println(result.get());
     }
*/
        //������ѯ��ʽ�����ϱ������ʽ��������ӡ�����Ѿ���ɵĽ����
        while (!futureResults.isEmpty()) {
            Iterator<Future<String>> it = futureResults.iterator();
            while (it.hasNext()) {
                Future<String> result = it.next();
                if (result.isDone()) {
                    System.out.println(result.get());
                    it.remove();
                }
            }
        }
        executorService.shutdown();
    }
}

class LookupTask implements Callable<String> {
    public static final String BLACKHOLE = "sbl.spamhaus.org";
    String addr;

    public LookupTask(String addr) {
        this.addr = addr;
    }

    public String call() {
        try {
            InetAddress address = InetAddress.getByName(addr);
            byte[] quad = address.getAddress();// ��ȡ������IP��ַ
            String query = BLACKHOLE;// �ڶ��б�

            //���������ַ���ֽڣ�����Ӻڶ��������
            //�������IP��ַ"108.33.56.27"��
            //query��ȡֵΪ"27.56.33.108.sbl.spamhaus.org"
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            //���������ַ
            InetAddress.getByName(query);

            return addr + "����֪�������ʼ�������";
        } catch (UnknownHostException e) {
            return addr + "����֪�ĺϷ��ʼ�������";
        }
    }
}

/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/