package example.service;

import example.config.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoctorService {

    public static void showDoctors() {
        try (Connection conn = DBConnection.getConnection()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");

            System.out.println("\nВрачи:");
            System.out.println("ID | Имя | Специализация");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("specialization")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}