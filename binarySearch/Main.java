/** двоичный поиск
 * В первой строке даны целое число и массив n различных натуральных чисел в порядке возрастания,
 * во второй — целое число k натуральных чисел.
 * Для каждого i от 1 до k необходимо вывести индекс [1...n] или -1, если такого j нет
 * Sample Input:
 * 5 1 5 8 12 13
 * 5 8 1 23 1 11
 * Sample Output:
 * 3 1 -1 1 -1
 */

package binarySearch;

import java.util.Scanner;

public class Main {
    static int[] array;
    static int[] search;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arrayData = scanner.nextLine().split(" ");
        String[] searchData = scanner.nextLine().split(" ");

        array = new int[Integer.parseInt(arrayData[0])];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(arrayData[i+1]);
        }

        search = new int[Integer.parseInt(searchData[0])];
        for (int i = 0; i < search.length; i++) {
            search[i] = Integer.parseInt(searchData[i+1]);
            System.out.print(search(array, search[i]) + " ");
        }

    }

    public static int search (int[] array, int number) {
        int left = 0;
        int right = array.length - 1;
        int middle = 0;

        while (left <= right) {

            middle = (left + right) / 2;

            if(number == array[middle]) return middle + 1;
            else if(number <= array[middle]) right = middle - 1;
            else if(number >= array[middle]) left = middle + 1;
        }
        return -1;
    }
}
