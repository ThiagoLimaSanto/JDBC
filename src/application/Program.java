package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DBIntegrityException;

public class Program {
    public static void main(String[] args) {
        try (Connection conn = DB.getconnection();
                PreparedStatement st = conn.prepareStatement(
                        "Delete from department "
                                + "where "
                                + "id = ?");) {
            
            st.setInt(1, 6);
            int rows = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rows);
        } catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        }
    }
}
