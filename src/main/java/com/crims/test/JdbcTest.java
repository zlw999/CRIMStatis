package com.crims.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTest {

    public static void main(String[] args) {
        //测试JDBC
        //加载注册成功
        try {
            //每一种数据库都需要提供不同的驱动名
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // 获取连接地址
            String strUrl = "jdbc:sqlserver://192.168.22.237:1433;database=CRNIVMLdbs(V4.0)";//由协议和地址名称组成
            // 2.连接数据库；用户名，密码，URL数据库地址，得到连接Connection
            Connection conn = DriverManager.getConnection(strUrl, "sa", "creal");
            System.out.println("连接成功");

            List<Map<String, Object>> list = new ArrayList<>();

            Statement statement = conn.createStatement();

//            String sql = "SELECT dbo.[sip-channel-map-16].channelcode, dbo.[conf-channel].channelname\n" +
//                    "FROM dbo.[conf-channel] INNER JOIN\n" +
//                    "dbo.[sip-channel-map-16] ON dbo.[conf-channel].channelid = dbo.[sip-channel-map-16].channelid";

            String sql = "SELECT\n" +
                    "\tdbo.[sip-nodedevice-map-16].nodedevicecode,\n" +
                    "\tdbo.[conf-node].nodename,\n" +
                    "\tdbo.[conf-node].DvrType,\n" +
                    "\tdbo.[conf-node].factoryno AS factoryno,\n" +
                    "\tdbo.[conf-node].ip,\n" +
                    "\tdbo.[conf-node].lisport,\n" +
                    "\tdbo.[conf-node].[user],\n" +
                    "\tdbo.[conf-node].password,\n" +
                    "\tdbo.[conf-node].[video-cnum] AS videonum \n" +
                    "FROM\n" +
                    "\tdbo.[sip-nodedevice-map-16]\n" +
                    "\tINNER JOIN dbo.[conf-node] ON dbo.[sip-nodedevice-map-16].nodeid = dbo.[conf-node].nodeid \n" +
                    "WHERE\n" +
                    "\t(\n" +
                    "\tdbo.[conf-node].nodeid & 0xFF = 1)";
            ResultSet rs = statement.executeQuery(sql);//执行查询语句，接收结果集

            ResultSetMetaData md = rs.getMetaData();//获取整个数据集的对象
            int columnCount = md.getColumnCount();//获取数据集的大小
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }

            int a = (65537 & 0xFFFF);
            System.out.println("a"+a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
