package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {
    public static void main(String[] args) {
        try (Connection conn = DB.getconnection();
                PreparedStatement st = conn.prepareStatement(
                        "Update seller " +
                                "set basesalary = basesalary + ? " +
                                "where " +
                                "(departmentid = ?)");) {

            st.setDouble(1, 200.00);
            st.setInt(2, 2);

            int rows = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
