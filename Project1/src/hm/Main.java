package hm;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        boolean choice = true;
                do{
                    System.out.println("Введите число от 1 до 99:");
                    int num = sc.nextInt();
                    System.out.println("Ваша сумма: ");
                    int lastTwo = num % 100;
                    int lastOne = num % 10;

                    if (lastTwo >= 11 && lastTwo <= 14) {
                        System.out.println(num + " копеек");
                    } else if (lastOne == 1) {
                        System.out.println(num + " копейка");
                    } else if (lastOne >= 2 && lastOne <= 4) {
                        System.out.println(num + " копейки");
                    } else {
                        System.out.println(num + " копеек");
                    }
                    System.out.println("Желаете ввести еще одну сумму? 1 - да. 0 - нет.");
                    byte a = sc.nextByte();
                    switch (a){
                        case 1 -> choice = true;
                        case 0 -> choice = false;
                    }
                    }while (choice == true);

        sc.close();
    }
    }

