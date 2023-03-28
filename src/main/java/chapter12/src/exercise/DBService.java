package chapter12.src.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class DBService {

    ArrayList<Customer> arrayCustomers = null;
    final int PER_PAGE_SIZE = 5;//ÿ��ҳ��ĳ��ȣ���ÿ��ҳ����ʾ�ļ�¼��Ŀ
    ConnectionPool pool = new ConnectionPoolImpl2();

    public ResultSet getAllCustomers() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            statement = con.createStatement();

            String sql = "select ID,NAME,AGE,ADDRESS from CUSTOMERS order by ID";
            rs = statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int getTotalSize() {  //���CUSTOMERS����ܵļ�¼��
        //�õ���ѯ�Ľ����
        ResultSet rs = getAllCustomers();
        if (rs == null) return 0;

        int totalsize = 0;//CUSTOMERS����ܵļ�¼��
        try {
            rs.last();  // ���α��ƶ����� ResultSet ��������һ��
            //  ��ȡ��ǰ�б��(�Ƚ��α��Ƶ����һ�У�
            // Ȼ���ٻ�ȡ���һ�е��±꣬���ɵõ�����CUSTOMERS��ļ�¼��)
            totalsize = rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalsize;
    }

    /**
     * �õ��ܹ���ҳ��
     */
    public int getPagesNum() {
        int totalsize = (Integer) getTotalSize() / PER_PAGE_SIZE;
        return (totalsize + 1);
    }

    /**
     * ���ض�ҳ������Customer����װ��ArrayList�б���
     * ����pageNumberָ���ض���ҳ��
     */
    public ArrayList<Customer> getPerPageData(int pageNumber) {
        //�õ����пͻ��Ľ����
        ResultSet rs = getAllCustomers();
        //����һ��װ�ͻ�����ļ���
        ArrayList<Customer> arrayCustomers = new ArrayList<Customer>();
        int totalsize = getTotalSize();

        int id = 0;//ID��
        String name = null;//����
        int age = 0; //����
        String address = null;//��ַ

        if (PER_PAGE_SIZE * (pageNumber - 1) < totalsize) {
            int start = PER_PAGE_SIZE * (pageNumber - 1) + 1;  //��ǰҳ��������¼�ڽ�����е�λ��
            int end = 0;  // //��ǰҳ�����һ����¼�ڽ�����е�λ��
            if (PER_PAGE_SIZE * pageNumber > totalsize) {
                end = totalsize;
            } else {
                end = PER_PAGE_SIZE * pageNumber;
            }
            for (int i = start; i <= end; i++) {
                try {
                    rs.absolute(i);
                    id = rs.getInt(1);
                    name = rs.getString(2);
                    age = rs.getInt(3);
                    address = rs.getString(4);
                    Customer c = new Customer(id, name, address, age);
                    arrayCustomers.add(c);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("������Χ");
        }
        return arrayCustomers;
    }
}