package hm3;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("Введите размер массива: ");
        short arrSize = sc.nextShort();
        int array[] = new int[arrSize];
        while(isRunning){
            System.out.println("Меню: \n" +
                    "1. Ввод элементов массива\n" +
                    "2. Отображение массива\n" +
                    "3. Поиск элемента в массиве\n" +
                    "4. Сортировка \n" +
                    "5. Выход");
            short a = sc.nextShort();

            switch (a) {
                case 1: {
                    System.out.println("Введите элементы массива: ");
                    for (int i = 0; i < array.length; i++) {
                        System.out.println("Элемент " + i + " ");
                        array[i] = sc.nextInt();
                    }

                }
                break;
                case 2: {
                    System.out.println("Ваш массив: " + Arrays.toString(array));
                }
                break;
                case 3: int index = findElement(array);
                    if(index != -1){
                        System.out.println("Ваш элемент найден на индексе: " + index);
                    } else{
                        System.out.println("Ваш элемент не найден");
                    }
                    break;
                case 4: {Arrays.sort(array);
                    System.out.println("Отсортированный массив: " + Arrays.toString(array));}
                break;
                case 5: System.out.println("Выход из программы...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }

    }

    static int findElement(int[] arr){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число для поиска: ");
        int search = sc.nextInt();
        int index = -1;
        for(int i = 0; i < arr.length; i++){
            if (search == arr[i]){
                index = i;
                return index;
            }
        }
        return -1;
    }

}
