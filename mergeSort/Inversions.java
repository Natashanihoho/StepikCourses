/** число инверсий
 * Первая строка содержит число n,  вторая — массив A[1...n], содержащий натуральные числа
 * Необходимо посчитать число пар индексов 1 <= i < j <= n, , для которых A[i] > A[j].
 * (Такая пара элементов называется инверсией массива. Количество инверсий в массиве является
 * в некотором смысле его мерой неупорядоченности: например, в упорядоченном по неубыванию массиве
 * инверсий нет вообще, а в массиве, упорядоченном по убыванию, инверсию образуют каждые два элемента.)
 * Sample Input:
 * 5
 * 2 3 9 2 9
 * Sample Output:
 * 2
 */
package mergeSort;


import java.util.Scanner;

public class Inversions {
    static int[] array;
    static int counter = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        array = new int[Integer.parseInt(scanner.nextLine())];
        String[] splittedLine = scanner.nextLine().split(" ");
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(splittedLine[i]);
        }

        mergeSort(array, array.length);
        System.out.println(counter);
    }
// 8
//4 8 1 2 6 7 9 1
    static void mergeSort(int[] a, int n) {
        if(n < 2) return;

        int middle = n / 2;
        int[] left = new int[middle];
        int[] right = new int[n-middle];

        for (int i = 0; i < middle; i++) {
            left[i] = a[i];
        }
        for (int i = middle; i < n; i++) {
            right[i - middle] = a[i];
        }
        mergeSort(left, middle);
        mergeSort(right, n-middle);

        merge(a, left, right, middle, n - middle);

    }

    static void merge(int[] a, int[] left, int[] right, int l, int r) {
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < l && j < r) {
            if(left[i] <= right[j]) {
                a[k++] = left[i++];
            }
            else {
                a[k++] = right[j++];
                counter+=l-i;
            }
        }

        while(i < l) a[k++] = left[i++];
        while(j < r) a[k++] = right[j++];
    }
}


