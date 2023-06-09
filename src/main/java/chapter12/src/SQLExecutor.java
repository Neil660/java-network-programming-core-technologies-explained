package chapter12.src;

import java.sql.*;
import java.io.*;

public class SQLExecutor {
    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            System.out.println("���ṩSQL�ű��ļ���");
            return;
        }
        String sqlfile = args[0];

        ConnectionProvider provider = new ConnectionProvider();
        Connection con = provider.getConnection();
        Statement stmt = con.createStatement();
        BufferedReader reader = new BufferedReader(new FileReader(new File(sqlfile)));
        try {
            String data = null;
            String sql = "";
            while ((data = reader.readLine()) != null) {
                data = data.trim(); //ɾ����ͷ���β�Ŀո�
                if (data.length() == 0) continue; //���Կ���
                sql = sql + data;
                if (sql.substring(sql.length() - 1).equals(";")) {
                    System.out.println(sql);
                    boolean hasResult = stmt.execute(sql);
                    if (hasResult)
                        showResultSet(stmt.getResultSet());
                    sql = "";
                }
            }
        } finally {
            con.close();
        }
    }
/*  
  public static void showResultSet(ResultSet rs) throws SQLException{
    ResultSetMetaData metaData=rs.getMetaData();
    int columnCount=metaData.getColumnCount();
    for(int i=1;i<=columnCount;i++){
      if(i>1)System.out.print(",");   
      System.out.print(metaData.getColumnLabel(i));
    }
    System.out.println();
    while(rs.next()){
      for(int i=1;i<=columnCount;i++){
        if(i>1)System.out.print(",");
        System.out.print(rs.getString(i));
      }
      System.out.println();
    }
    rs.close();
  }
*/

    public static void showResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(",");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(",");
                int type = metaData.getColumnType(i);
                switch (type) {
                    case Types.BIGINT:
                        System.out.print(rs.getLong(i));
                        break;
                    case Types.FLOAT:
                        System.out.print(rs.getFloat(i));
                        break;
                    case Types.VARCHAR:
                    default:
                        System.out.print(rs.getString(i));
                }
            }
            System.out.println();
        }
        rs.close();
    }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
