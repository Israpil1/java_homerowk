package hm4;

import java.util.Scanner;

public class Main {
    static void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите фразу для проверки: ");
        String p = sc.next();

        if (isPalindrome(p)) {
            System.out.println("Это палиндром!");
        } else {
            System.out.println("Это не палиндром");
        }
        sc.close();
    }
    public static boolean isPalindrome(String text) {

        String clean = text.toLowerCase().replaceAll("[^a-zA-Z0-9а-яА-Я]", "");

        StringBuilder sb = new StringBuilder(clean);
        String reversed = sb.reverse().toString();

        return clean.equals(reversed);
    }
}
