package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getconnection() {
        if (conn == null) {
            try {
                Properties p = loadProperties();
                String url = p.getProperty("dburl");
                conn = DriverManager.getConnection(url, p);
            } catch (SQLException e) {
                throw new dbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new dbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties p = new Properties();
            p.load(fs);
            return p;
        } catch (IOException e) {
            throw new dbException(e.getMessage());
        }
    }
}
