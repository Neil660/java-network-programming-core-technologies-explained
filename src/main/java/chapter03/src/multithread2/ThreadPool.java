package chapter03.src.multithread2;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {
    private boolean isClosed = false;  //�̳߳��Ƿ�ر�
    private LinkedList<Runnable> workQueue;  //��ʾ��������
    private static int threadPoolID;  //��ʾ�̳߳�ID
    private int threadID;  //��ʾ�����߳�ID

    public ThreadPool(int poolSize) { //poolSizeָ���̳߳��еĹ����߳���Ŀ
        super("ThreadPool-" + (threadPoolID++));
        setDaemon(true);
        workQueue = new LinkedList<Runnable>();  //������������
        for (int i = 0; i < poolSize; i++)
            new WorkThread().start();  //���������������߳�
    }

    /**
     * ���������м���һ���������ɹ����߳�ȥִ�и�����
     */
    public synchronized void execute(Runnable task) {
        if (isClosed) { //�̳߳ر������׳�IllegalStateException�쳣
            throw new IllegalStateException();
        }
        if (task != null) {
            workQueue.add(task);
            notify();  //��������getTask()�����еȴ�����Ĺ����߳�
        }
    }

    /**
     * �ӹ���������ȡ��һ�����񣬹����̻߳���ô˷���
     */
    protected synchronized Runnable getTask() throws InterruptedException {
        while (workQueue.size() == 0) {
            if (isClosed) return null;
            wait();  //�������������û�����񣬾͵ȴ�����
        }
        return workQueue.removeFirst();
    }

    /**
     * �ر��̳߳�
     */
    public synchronized void close() {
        if (!isClosed) {
            isClosed = true;
            workQueue.clear(); //��չ�������
            interrupt();  //�ж����еĹ����̣߳��÷����̳���ThreadGroup��
        }
    }

    /**
     * �ȴ������̰߳���������ִ����
     */
    public void join() {
        synchronized (this) {
            isClosed = true;
            notifyAll();  //���ѻ���getTask()�����еȴ�����Ĺ����߳�
        }

        Thread[] threads = new Thread[activeCount()];
        //enumerate()�����̳���ThreadGroup�࣬����߳����е�ǰ���л��ŵĹ����߳�
        int count = enumerate(threads);
        for (int i = 0; i < count; i++) { //�ȴ����й����߳����н���
            try {
                threads[i].join();  //�ȴ������߳����н���
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * �ڲ��ࣺ�����߳�
     */
    private class WorkThread extends Thread {
        public WorkThread() {
            //���뵽��ǰThreadPool�߳�����
            super(ThreadPool.this, "WorkThread-" + (threadID++));
        }

        public void run() {
            while (!isInterrupted()) {  //isInterrupted()�����̳���Thread�࣬�ж��߳��Ƿ��ж�
                Runnable task = null;
                try { //�õ�����
                    task = getTask();
                } catch (InterruptedException ex) {
                }

                // ���getTask()����null�����߳�ִ��getTask()ʱ���жϣ���������߳�
                if (task == null) return;

                try { //�������񣬲����쳣
                    task.run();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }//#while
        }//#run()
    }//#WorkThread��
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
