package lesson_2;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
//        System.out.print("Введите число");
//        int a = sc.nextInt();
//
//        System.out.printf("Вы ввели число: %d %n", a);
        sc.close();

        System.out.print("Им: ");
        String name = sc.nextLine();

        System.out.printf("Имя: %s%n", name);
        sc.close();
    }
}
