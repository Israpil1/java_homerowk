import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    interface DuplicateRemover {
        int[] removeDuplicates(int[] array);
    }

    public static void main(String[] args) {
        int[] ar = {2, 3, 4, 5, 6, 7, 7, 8, 8, 10};

        System.out.println("Исходный массив: " + Arrays.toString(ar));

        DuplicateRemover remover = (arr) -> IntStream.of(arr).distinct().toArray();

        int[] result = remover.removeDuplicates(ar);
        System.out.println("Массив без дубликатов: " + Arrays.toString(result));
    }
}