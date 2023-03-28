package chapter13.src.store;

import java.sql.*;

public interface DBService {
    /**
     * ���Statement����
     */
    public Statement getStatement() throws Exception;

    /**
     * �ر�Statement�����Լ���֮������Connection����
     */
    public void closeStatement(Statement stmt);

    /**
     * ִ��SQL update��delete��insert���
     */
    public void modifyTable(String sql) throws Exception;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̺��ļ������>>                       *
 * ����֧����ַ��www.javathinker.net                *
 ***************************************************/
