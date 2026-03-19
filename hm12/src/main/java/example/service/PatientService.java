package example.service;

import example.config.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class PatientService {

    public static void addPatient() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите имя: ");
        String name = sc.nextLine();

        System.out.print("Введите возраст: ");
        int age = sc.nextInt();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO patients (name, age) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);

            ps.executeUpdate();

            System.out.println("Пациент добавлен!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPatients() {
        try (Connection conn = DBConnection.getConnection()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients");

            System.out.println("\nПациенты:");
            System.out.println("ID | Имя | Возраст");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}