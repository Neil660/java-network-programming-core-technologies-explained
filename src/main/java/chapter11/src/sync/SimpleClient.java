package chapter11.src.sync;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleClient {
    public static void main(String args[]) throws Exception {

        Registry registry = LocateRegistry.getRegistry(1099);
        Stack stack = (Stack) registry.lookup("MyStack");
        Producer producer1 = new Producer(stack, "producer1");
        Producer producer2 = new Producer(stack, "producer2");
        Consumer consumer1 = new Consumer(stack, "consumer1");
    }
}

/**
 * �������߳�
 */
class Producer extends Thread {
    private Stack theStack;

    public Producer(Stack s, String name) {
        super(name);
        theStack = s;
        start();  //���������������߳�
    }

    public void run() {
        String goods;
        try {
            for (; ; ) {
                synchronized (theStack) {
                    goods = "goods" + (theStack.getPoint() + 1);
                    theStack.push(goods);
                }
                System.out.println(getName() + ": push " + goods + " to " + theStack.getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * �������߳�
 */
class Consumer extends Thread {
    private Stack theStack;

    public Consumer(Stack s, String name) {
        super(name);
        theStack = s;
        start();  //���������������߳�
    }

    public void run() {
        String goods;
        try {
            for (; ; ) {
                goods = theStack.pop();
                System.out.println(getName() + ": pop " + goods + " from " + theStack.getName());
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
