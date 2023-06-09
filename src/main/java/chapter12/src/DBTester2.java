package chapter12.src;

import java.sql.*;

public class DBTester2 {
    private ConnectionProvider provider;

    public DBTester2(ConnectionProvider provider) {
        this.provider = provider;
    }

    public void addCustomer(String name, int age, String address) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {
            con = provider.getConnection();
            stmt = con.createStatement();
            String sql = "insert into CUSTOMERS(NAME,AGE,ADDRESS) values("
                    + "'" + name + "'" + ","
                    + age + ","
                    + "'" + address + "'" + ")";

            stmt.execute(sql);
        } finally {
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    public void deleteCustomer(String name) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {
            con = provider.getConnection();
            stmt = con.createStatement();
            String sql = "delete from CUSTOMERS where NAME='" + name + "'";
            stmt.execute(sql);
        } finally {
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    public void printAllCustomers() throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = provider.getConnection();
            stmt = con.createStatement();
            //��ѯ��¼
            rs = stmt.executeQuery("SELECT ID,NAME,AGE,ADDRESS from CUSTOMERS");
            //�����ѯ���
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String address = rs.getString(4);

                //��ӡ����ʾ������
                System.out.println("id=" + id + ",name=" + name + ",age=" + age + ",address=" + address);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    private void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeStatement(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        DBTester2 tester = new DBTester2(new ConnectionProvider());
        tester.addCustomer("С��", 20, "�Ϻ�");
        tester.printAllCustomers();
        tester.deleteCustomer("С��");
    }
}

/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
