package org.charlestech.fin.prototype;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;

/**
 * Created by banzhu on 14-2-3.
 */
public class DataModelUtil {
    static String DB_URL = "jdbc:mysql://115.28.140.140:3306/fin";
    static String USER_NAME = "root";
    static String PASSWORD = "gulliver";
    static Connection CONNECTION;

    public static int insertFinancialStatement(FinancialStatement financialStatement) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        int num = 0;
        try {
            String sql = insertSql(financialStatement);
            System.out.println(sql);
            CONNECTION = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            PreparedStatement pStmt = CONNECTION.prepareStatement(sql);
            CONNECTION.setAutoCommit(false);
            pStmt.setTimestamp(1, financialStatement.getReleaseTime());
            num = pStmt.executeUpdate();
            CONNECTION.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                CONNECTION.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            CONNECTION.close();
        }
        return num;
    }

//    public static int insertIndustryCode(List<List<String>> industryCode) throws Exception{
//        Class.forName("com.mysql.jdbc.Driver");
//        int num = 0;
//        try{
//
//        }
//    }

    private static String insertSql(FinancialStatement financialStatement) {
        StringBuffer sbColumn = new StringBuffer();
        StringBuffer sbValue = new StringBuffer();
        sbColumn.append("INSERT INTO ");
        if ("BALANCE".equalsIgnoreCase(financialStatement.getStatementType())) {
            sbColumn.append("balance_statement ( ");
        } else if ("INCOME".equalsIgnoreCase(financialStatement.getStatementType())) {
            sbColumn.append("income_statement ( ");
        } else if ("CASH".equalsIgnoreCase(financialStatement.getStatementType())) {
            sbColumn.append("cash_statement ( ");
        }

        sbColumn.append("stock_id, release_time, institution_type");
        sbValue.append("values(" + financialStatement.getStockId() + ", ?, 'Company' ");
        Set<Map.Entry<FinLookupSeed, Double>> entrySet = financialStatement.getDataMap().entrySet();
        for (Map.Entry<FinLookupSeed, Double> entry : entrySet) {
            sbColumn.append(", " + entry.getKey().getColumnName());
            sbValue.append(", " + entry.getValue().toString());
        }
        sbColumn.append(")");
        sbValue.append(")");
        sbColumn.append(sbValue);
        return sbColumn.toString();
    }

}
