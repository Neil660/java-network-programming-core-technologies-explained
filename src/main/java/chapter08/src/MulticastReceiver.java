package chapter08.src;

import java.net.*;
import java.io.*;

public class MulticastReceiver {
    public static void main(String[] args) throws Exception {
        String address = "224.0.1.1";
        int port = 4000;
        if (args.length == 2) {
            address = args[0];
            port = Integer.parseInt(args[1]);
        }
        InetAddress group = InetAddress.getByName(address);
        MulticastSocket ms = null;

        try {
            ms = new MulticastSocket(port);
            ms.joinGroup(group);

            byte[] buffer = new byte[8192];
            while (true) {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ms.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ms != null) {
                try {
                    ms.leaveGroup(group);
                    ms.close();
                } catch (IOException e) {
                }
            }
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
