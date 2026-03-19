package example.service;

import example.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AppointmentService {

    public static void addAppointment() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите ID пациента: ");
        int patientId = sc.nextInt();

        System.out.print("Введите ID врача: ");
        int doctorId = sc.nextInt();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO appointments (patient_id, doctor_id, date) VALUES (?, ?, NOW())";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);

            ps.executeUpdate();

            System.out.println("Запись на прием создана!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}