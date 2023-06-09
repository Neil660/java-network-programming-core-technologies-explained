package chapter12.src.exercise;

import java.sql.*;

public class ConnectionProvider {
    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    public ConnectionProvider() {
        JDBC_DRIVER = PropertyReader.get("JDBC_DRIVER");
        DB_URL = PropertyReader.get("DB_URL");
        DB_USER = PropertyReader.get("DB_USER");
        DB_PASSWORD = PropertyReader.get("DB_PASSWORD");
        try {
            Class jdbcDriver = Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return con;
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
