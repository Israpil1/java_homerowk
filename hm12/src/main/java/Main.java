import example.service.PatientService;
import example.service.DoctorService;
import example.service.AppointmentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nСИСТЕМА УПРАВЛЕНИЯ БОЛЬНИЦЕЙ");
            System.out.println("1. Добавить пациента");
            System.out.println("2. Просмотр пациентов");
            System.out.println("3. Посмотреть врачей");
            System.out.println("4. Записаться на прием");
            System.out.println("5. Выход");
            System.out.print("Ваш выбор: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    PatientService.addPatient();
                    break;
                case 2:
                    PatientService.showPatients();
                    break;
                case 3:
                    DoctorService.showDoctors();
                    break;
                case 4:
                    AppointmentService.addAppointment();
                    break;
                case 5:
                    System.out.println("СПАСИБО!");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }

        } while (choice != 5);
    }
}