package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseActivity extends AppCompatActivity {
    Thread mThread;
    String UserName = "Mobile_User";//用户名
    String Password = "hymobile.123";//密码
    Connection con = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://115.159.108.207:3306/kosingdb30_mobile?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false", UserName, Password);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        mThread.start();
        while (con==null){
            Log.d("sss","ddddd");
        };
        String sql = "SELECT * FROM sysmainmenu";//查询表名为“table_test”的所有内容
        Statement stmt = null;//创建Statement
        try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);//ResultSet类似Cursor
         while (rs.next()) {//<code>ResultSet</code>最初指向第一行
              System.out.println(rs.getString("BtnName"));//输出第n行，列名为“test_id”的值
         } rs.close();
         stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
