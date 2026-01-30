package hm2;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean answer = true;
        do {
            System.out.println("Вычисление площади фигур\nВыбор фигуры\n1.Треугольник\n2.прямоугольник\n3.Круг\nВведите число:");
            short figure = sc.nextShort();
            switch (figure) {
                case 1: {
                    System.out.println("Введите сторону а:");
                    int a = sc.nextInt();
                    System.out.println("Введите сторону b:");
                    int b = sc.nextInt();
                    System.out.println("Введите сторону c:");
                    int c = sc.nextInt();
                    if (((a + b) > c) && ((a + c) > b) && ((b + c) > a)){
                        int p = (a + b + c) / 2;
                        double res = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                        System.out.println("Площадь треугольника: " + res);
                    }else{
                        System.out.println("Некорректные данные. введите новые.");
                    }

                }
                break;
                case 2: {
                    System.out.println("Введите сторону а:");
                    int a = sc.nextInt();
                    System.out.println("Введите сторону b:");
                    int b = sc.nextInt();

                    int res = a * b;
                    System.out.println("Площадь прямоугольника: " + res);
                }
                break;
                case 3: {
                    System.out.println("Введите радиус:");
                    int a = sc.nextInt();

                    double res = Math.PI * (a * a);
                    System.out.println("Площадь круга: " + res);
                }
                break;
                default:
                    System.out.println("Вы ввели неверное число");
            }
            System.out.println("Желаете рассчитать площадь еще одной фигуры? 1 - да\n0 - нет");
            short choice = sc.nextShort();
            switch(choice){
                case 1 : {
                    answer = true;
                }
                break;
                case 0 : {
                    answer = false;
                }
                break;
                default : System.out.println("Вы ввели неверное число");
            }
        }while(answer == true);
        sc.close();
    }
}

