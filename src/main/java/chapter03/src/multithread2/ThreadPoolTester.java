package chapter03.src.multithread2;

public class ThreadPoolTester {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "�÷�: java ThreadPoolTest numTasks poolSize");
            System.out.println(
                    "  numTasks - integer: �������Ŀ");
            System.out.println(
                    "  numThreads - integer: �̳߳��е��߳���Ŀ");
            return;
        }
        int numTasks = Integer.parseInt(args[0]);
        int poolSize = Integer.parseInt(args[1]);

        ThreadPool threadPool = new ThreadPool(poolSize);  //�����̳߳�

        // ��������
        for (int i = 0; i < numTasks; i++)
            threadPool.execute(createTask(i));

        threadPool.join();  //�ȴ������߳�������е�����
        // threadPool.close(); //�ر��̳߳�
    }//#main()

    /**
     * ������һ���򵥵�����(��ӡID)
     */
    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            public void run() {
                System.out.println("Task " + taskID + ": start");
                try {
                    Thread.sleep(500);  //����ִ��һ�������ʱ��
                } catch (InterruptedException ex) {
                }
                System.out.println("Task " + taskID + ": end");
            }
        };
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
