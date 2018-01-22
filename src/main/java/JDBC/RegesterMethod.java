package JDBC;

import org.junit.Assert;

import java.sql.*;

/**
 * Created by kingcall 2017年-09月-01日,11时-23分
 * Descibe
 */
public class RegesterMethod {
    public static void main(String[] args) throws SQLException {
        String sql="select * from city";
        ResultSet result= prepared(getConnection(),sql).executeQuery();
        while (result.next()){
            System.out.println(result.getString("Name")+"\t"+result.getString("Population"));
        }

    }
    public static Connection getConnection(){
        Connection con=null;
        getDriver2();
        try {
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC","root","www1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void getDriver() {
        try {
            DriverManager.registerDriver( new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getDriver2() {
            System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
    }
    public static void getDriver3() {
        try {
            Class.forName("com.jdbd.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static PreparedStatement prepared(Connection connection,String sql){
        PreparedStatement preparedStatement=null;
        try {
            if (connection.isClosed()){
                connection=getConnection();
                throw new Error("链接已经被关闭，已经打开");

            }
            else {
                 preparedStatement=connection.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
