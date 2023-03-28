package chapter13.src.store;

public interface StoreController {
    /**
     * �����ѯ�ͻ��Ķ���
     */
    public void handleGetCustomerGesture(long id);

    /**
     * ������ӿͻ��Ķ���
     */
    public void handleAddCustomerGesture(Customer c);

    /**
     * ����ɾ���ͻ��Ķ���
     */
    public void handleDeleteCustomerGesture(Customer c);

    /**
     * ������¿ͻ��Ķ���
     */
    public void handleUpdateCustomerGesture(Customer c);

    /**
     * �����г����пͻ��嵥�Ķ���
     */
    public void handleGetAllCustomersGesture();
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
