package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.dbException;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getconnection();

            conn.setAutoCommit(false);
            
            st = conn.createStatement();

            int rows1 = st.executeUpdate("Update seller set  basesalary = 2090 where departmentid = 1");
            
            int rows2 = st.executeUpdate("Update seller set  basesalary = 3090 where departmentid = 2");

            conn.commit();
            
            System.out.println(rows1);
            System.out.println(rows2);
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new dbException("Trasaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new dbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }finally{
            DB.closeStatment(st);
            DB.closeConnection();
        }
    }
}
