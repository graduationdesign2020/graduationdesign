package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {
    private Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/GDMS?useUnicode=true&characterEncoding=utf8";//数据库连接字符串，这里的deom为数据库名

    private static final String NAME = "root";//登录名
    private static final String PASSWORD = "520lixuan";//密码


    public void getConnection() {
        //1.加载驱动
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
            e.printStackTrace();
        }
        try
        {
            con = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("获取数据库连接成功！");

        }
        catch (SQLException e)
        {
            System.out.println("获取数据库连接失败！");
            //添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误
            e.printStackTrace();
        }
    }

    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                con = null;
            }
        }
    }

    public List<user> getSelect()
    {
        String sql = "select * from wechatusers";
        PreparedStatement pst = null;
        // 定义一个list用于接受数据库查询到的内容
        List<user> list = new ArrayList<>();
        try
        {
            pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                user theuser = new user();
                theuser.setId(rs.getString("id"));
                theuser.setWechat_id(rs.getString("wechat_id"));
                theuser.setAuth(rs.getString("auth"));
                list.add(theuser);
            }
        }
        catch (Exception e)
        {
            System.out.printf("don't get any");
        }
        return list;
    }
}
