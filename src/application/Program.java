package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DB.getconnection();

            // ps = conn.prepareStatement(
            //         "INSERT INTO SELLER" +
            //                 "(NAME, EMAIL, BIRTHDATE, BASESALARY, DEPARTMENTID)" +
            //                 "VALUES" +
            //                 "(?, ?, ?, ?, ?)",
            //                 Statement.RETURN_GENERATED_KEYS);

            // ps.setString(1, "Joao");
            // ps.setString(2, "Joao@gmail.com");
            // ps.setDate(3, new Date(sdf.parse("18/03/2003").getTime()));
            // ps.setDouble(4, 3400.00);
            // ps.setInt(5, 2);

            ps = conn.prepareStatement("insert into department (name) values ('d1'), ('d2')",
                Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = " + id);
                }
            }else{
                System.out.println("No rows affected! ");
            }

        } catch (SQLException s) {
            s.printStackTrace();
        }finally{
            DB.closeStatment(ps);
            DB.closeConnection();
        }
    }
}
