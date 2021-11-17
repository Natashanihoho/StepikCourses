package dynamicProgramming.largestMultipleSubsequence;

/** Задача на программирование: наибольшая последовательнократная подпоследовательность
 * Дано целое число 1 ≤ n ≤ 10^3 и массив A[1...n] натуральных чисел, не превосходящих 2 * 10^9
 * Выведите максимальное 1 ≤ k ≤ n, для которого найдётся подпоследовательность,
 * в которой каждый элемент делится на предыдущий
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] array;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(searchSubMaxSize(array));
    }

    static int searchSubMaxSize (int[] array) {
        int[] countArray = new int[array.length];
        Arrays.fill(countArray, 1);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if(array[i] % array[j] == 0 && countArray[j] + 1 > countArray[i]) {
                    countArray[i] = countArray[j] + 1;
                }
            }
        }
        return Arrays.stream(countArray).max().getAsInt();
    }
}

