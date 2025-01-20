package Pc_database.DBconnection;
import java.sql.*;
public class DBConnection {
    public static Connection getConnection() throws Exception{
        Connection conn;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pc_databases", "root", "#password");
        return conn;
    }

}