package countingSort;

import java.util.Scanner;

/** Задача на программирование: сортировка подсчётом
 * Первая строка содержит число 1 ≤ n ≤ 10^4, вторая — n натуральных чисел, не превышающих 10.
 * Выведите упорядоченную по неубыванию последовательность этих чисел.
 * Sample Input:
 * 5
 * 2 3 9 2 9
 * Sample Output:
 * 2 2 3 9 9
 */
public class Sorter {
    static int[] originalArray;
    static int[] sortedArray;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        originalArray = new int[n];
        String[] splittedString = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            originalArray[i] = Integer.parseInt(splittedString[i]);
        }
        sortedArray = new int[originalArray.length];
        sortedArray = countSort(originalArray);
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }

    private static int[] countSort(int[] originalArray) {
        int[] countingArray = new int[11];
        int[] resultArray = new int[originalArray.length];

        for (int i = 0; i < originalArray.length; i++) {
            countingArray[originalArray[i]] += 1;
        }

        int k = 0;
        for (int i = 0; i < countingArray.length; i++) {
            for (int j = 0; j < countingArray[i]; j++) {
                resultArray[k++] = i;
            }
        }
        return resultArray;
    }
}
