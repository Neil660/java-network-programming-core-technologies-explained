package chapter12.src;

import java.sql.*;

public class DBTester3 {
    private ConnectionProvider provider;
    private Connection con;
    private PreparedStatement addStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement findStmt;

    public DBTester3(ConnectionProvider provider) throws SQLException {
        this.provider = provider;
        con = provider.getConnection();
    }

    public void addCustomer(String name, int age, String address) throws SQLException {
        String sql = "insert into CUSTOMERS(NAME,AGE,ADDRESS) values(?,?,?)";
        if (addStmt == null)
            addStmt = con.prepareStatement(sql);
        addStmt.setString(1, name);
        addStmt.setInt(2, age);
        addStmt.setString(3, address);
        addStmt.execute();
    }

    public void deleteCustomer(String name) throws SQLException {
        String sql = "delete from CUSTOMERS where NAME=?";
        if (deleteStmt == null)
            deleteStmt = con.prepareStatement(sql);
        deleteStmt.setString(1, name);
        deleteStmt.execute();
    }

    public void findCustomer(String name, int age) throws SQLException {
        String sql = " select ID,NAME,AGE,ADDRESS from CUSTOMERS where NAME=? and AGE=?";
        if (findStmt == null)
            findStmt = con.prepareStatement(sql);
        findStmt.setString(1, name);
        findStmt.setInt(2, age);
        //��ѯ��¼
        ResultSet rs = findStmt.executeQuery();
        try {
            //�����ѯ���
            while (rs.next()) {
                long id = rs.getLong(1);
                name = rs.getString(2);
                age = rs.getInt(3);
                String address = rs.getString(4);

                //��ӡ����ʾ������
                System.out.println("id=" + id + ",name=" + name + ",age=" + age + ",address=" + address);
            }
        } finally {
            rs.close();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        DBTester3 tester = new DBTester3(new ConnectionProvider());
        tester.addCustomer("С��", 20, "�Ϻ�");
        tester.addCustomer("С��", 30, "�Ϻ�");
        tester.findCustomer("С��", 20);
        tester.findCustomer("С��", 30);
        tester.deleteCustomer("С��");
        tester.deleteCustomer("С��");

        tester.close();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
