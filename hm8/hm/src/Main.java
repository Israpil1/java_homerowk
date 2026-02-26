import java.util.Scanner;

public static void main(String[] args) {
    System.out.println("Введите предложение");
    Scanner input = new Scanner(System.in);
    String sentence = input.nextLine();
    wordCount counter = (s) -> {
        return s.split(" ").length;
    };


    int Result = counter.Count(sentence);
    System.out.println("В предложении " + ' ' + sentence + ' ' + Result + "слов(а)");
    input.close();
}