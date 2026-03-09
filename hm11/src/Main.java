import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            if (choice == 0) {
                System.out.println("До свидания!");
                break;
            }

            processChoice(choice);
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            АВТОМОБИЛИ - МЕНЮ");
        System.out.println("=".repeat(50));
        System.out.println("1. Показать все автомобили");
        System.out.println("2. Показать всех производителей");
        System.out.println("3. Автомобили по году выпуска");
        System.out.println("4. Автомобили по производителю");
        System.out.println("5. Фильтр по цвету");
        System.out.println("6. Фильтр по объему двигателя");
        System.out.println("7. Фильтр по типу кузова");
        System.out.println("8. Добавить новый автомобиль");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void processChoice(int choice) {
        switch (choice) {
            case 1: showAllCars(); break;
            case 2: showAllManufacturers(); break;
            case 3: showCarsByYear(); break;
            case 4: showCarsByManufacturer(); break;
            case 5: showCarsByColour(); break;
            case 6: showCarsByVolume(); break;
            case 7: showCarsByType(); break;
            case 8: addNewCar(); break;
            default: System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private static void showAllCars() {
        List<Automobile> cars = new ArrayList<>();
        String query = "SELECT * FROM automobiles";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Automobile car = new Automobile(
                        rs.getInt("id"),
                        rs.getString("manufacturer"),
                        rs.getString("brand"),
                        rs.getDouble("volume"),
                        rs.getInt("year"),
                        rs.getString("colour"),
                        rs.getString("type")
                );
                cars.add(car);
            }

            if (cars.isEmpty()) {
                System.out.println("В базе нет автомобилей.");
            } else {
                System.out.println("\nВсе автомобили (" + cars.size() + " шт.):");
                for (Automobile car : cars) {
                    System.out.println(car);
                }
            }

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showAllManufacturers() {
        String query = "SELECT DISTINCT manufacturer FROM automobiles ORDER BY manufacturer";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nПроизводители:");
            int count = 0;
            while (rs.next()) {
                System.out.println("   • " + rs.getString("manufacturer"));
                count++;
            }
            if (count == 0) {
                System.out.println("   Нет данных");
            } else {
                System.out.println("   Всего: " + count);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showCarsByYear() {
        System.out.print("Введите год (например, 2022): ");
        try {
            int year = Integer.parseInt(scanner.nextLine());
            String query = "SELECT * FROM automobiles WHERE Year = ?";

            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, year);
                ResultSet rs = pstmt.executeQuery();

                boolean found = false;
                System.out.println("\nАвтомобили " + year + " года:");
                while (rs.next()) {
                    found = true;
                    Automobile car = new Automobile(
                            rs.getInt("id"), rs.getString("manufacturer"),
                            rs.getString("Brand"), rs.getDouble("Volume"),
                            rs.getInt("Year"), rs.getString("Colour"),
                            rs.getString("Type")
                    );
                    System.out.println("   " + car);
                }

                if (!found) {
                    System.out.println("   Автомобили " + year + " года не найдены.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: год должен быть числом.");
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showCarsByManufacturer() {
        System.out.print("Введите производителя (например, Toyota): ");
        String manufacturer = scanner.nextLine();
        String query = "SELECT * FROM automobiles WHERE manufacturer = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, manufacturer);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            System.out.println("\nАвтомобили " + manufacturer + ":");
            while (rs.next()) {
                found = true;
                Automobile car = new Automobile(
                        rs.getInt("id"), rs.getString("manufacturer"),
                        rs.getString("Brand"), rs.getDouble("Volume"),
                        rs.getInt("Year"), rs.getString("Colour"),
                        rs.getString("Type")
                );
                System.out.println("   " + car);
            }

            if (!found) {
                System.out.println("   Автомобили " + manufacturer + " не найдены.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showCarsByColour() {
        System.out.print("Введите цвет: ");
        String colour = scanner.nextLine();
        String query = "SELECT * FROM automobiles WHERE Colour = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, colour);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            System.out.println("\nАвтомобили цвета " + colour + ":");
            while (rs.next()) {
                found = true;
                Automobile car = new Automobile(
                        rs.getInt("id"), rs.getString("manufacturer"),
                        rs.getString("Brand"), rs.getDouble("Volume"),
                        rs.getInt("Year"), rs.getString("Colour"),
                        rs.getString("Type")
                );
                System.out.println("   " + car);
            }

            if (!found) {
                System.out.println("   Автомобили цвета " + colour + " не найдены.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showCarsByVolume() {
        System.out.println("Выберите тип поиска:");
        System.out.println("1. Точное совпадение");
        System.out.println("2. Диапазон (от и до)");
        System.out.print("Ваш выбор: ");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            try {
                System.out.print("Введите объем (например, 2.0): ");
                double volume = Double.parseDouble(scanner.nextLine());
                String query = "SELECT * FROM automobiles WHERE Volume = ?";

                try (Connection conn = DatabaseConfig.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setDouble(1, volume);
                    ResultSet rs = pstmt.executeQuery();

                    boolean found = false;
                    System.out.println("\nАвтомобили с объемом " + volume + " л:");
                    while (rs.next()) {
                        found = true;
                        Automobile car = new Automobile(
                                rs.getInt("id"), rs.getString("manufacturer"),
                                rs.getString("Brand"), rs.getDouble("Volume"),
                                rs.getInt("Year"), rs.getString("Colour"),
                                rs.getString("Type")
                        );
                        System.out.println("   " + car);
                    }

                    if (!found) {
                        System.out.println("   Автомобили с объемом " + volume + " л не найдены.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: объем должен быть числом.");
            } catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

        } else if (choice.equals("2")) {
            try {
                System.out.print("Введите минимальный объем: ");
                double min = Double.parseDouble(scanner.nextLine());
                System.out.print("Введите максимальный объем: ");
                double max = Double.parseDouble(scanner.nextLine());

                String query = "SELECT * FROM automobiles WHERE Volume BETWEEN ? AND ?";

                try (Connection conn = DatabaseConfig.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setDouble(1, min);
                    pstmt.setDouble(2, max);
                    ResultSet rs = pstmt.executeQuery();

                    boolean found = false;
                    System.out.println("\nАвтомобили с объемом от " + min + " до " + max + " л:");
                    while (rs.next()) {
                        found = true;
                        Automobile car = new Automobile(
                                rs.getInt("id"), rs.getString("manufacturer"),
                                rs.getString("Brand"), rs.getDouble("Volume"),
                                rs.getInt("Year"), rs.getString("Colour"),
                                rs.getString("Type")
                        );
                        System.out.println("   " + car);
                    }

                    if (!found) {
                        System.out.println("   Автомобили не найдены.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: объем должен быть числом.");
            } catch (SQLException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    private static void showCarsByType() {
        System.out.println("Доступные типы:");
        System.out.println("1. седан");
        System.out.println("2. хэтчбэк");
        System.out.println("3. универсал");
        System.out.print("Выберите тип (1-3): ");

        String choice = scanner.nextLine();
        String type = null;

        switch (choice) {
            case "1": type = "седан"; break;
            case "2": type = "хетчбек"; break;
            case "3": type = "универсал"; break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        String query = "SELECT * FROM automobiles WHERE Type = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            System.out.println("\nАвтомобили типа \"" + type + "\":");
            while (rs.next()) {
                found = true;
                Automobile car = new Automobile(
                        rs.getInt("id"), rs.getString("manufacturer"),
                        rs.getString("Brand"), rs.getDouble("Volume"),
                        rs.getInt("Year"), rs.getString("Colour"),
                        rs.getString("Type")
                );
                System.out.println("   " + car);
            }

            if (!found) {
                System.out.println("   Автомобили типа \"" + type + "\" не найдены.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void addNewCar() {
        System.out.println("\nДобавление нового автомобиля");

        try {
            System.out.print("Производитель: ");
            String manufacturer = scanner.nextLine();

            System.out.print("Модель: ");
            String brand = scanner.nextLine();

            System.out.print("Объем двигателя: ");
            double volume = Double.parseDouble(scanner.nextLine());

            System.out.print("Год выпуска: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Цвет: ");
            String colour = scanner.nextLine();

            System.out.println("Тип кузова (1-седан, 2-хетчбек, 3-универсал): ");
            String typeChoice = scanner.nextLine();
            String type;
            switch (typeChoice) {
                case "1": type = "седан"; break;
                case "2": type = "хетчбек"; break;
                case "3": type = "универсал"; break;
                default:
                    System.out.println("Неверный тип. Используем 'седан'.");
                    type = "седан";
            }

            String query = "INSERT INTO automobiles (manufacturer, Brand, Volume, Year, Colour, Type) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DatabaseConfig.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, manufacturer);
                pstmt.setString(2, brand);
                pstmt.setDouble(3, volume);
                pstmt.setInt(4, year);
                pstmt.setString(5, colour);
                pstmt.setString(6, type);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Автомобиль успешно добавлен!");
                }

            } catch (SQLException e) {
                System.out.println("Ошибка при добавлении: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат числа.");
        }
    }
}