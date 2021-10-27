/** очередь с приоритетами
 * Первая строка входа содержит число операций.
 * Каждая из последующих n строк задают операцию одного из следующих двух типов:
 * Insert x, где x - целое число
 * ExtractMax
 * Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
 * Sample Input:
 * 6
 * Insert 200
 * Insert 10
 * ExtractMax
 * Insert 5
 * Insert 500
 * ExtractMax
 * Sample Output:
 * 200
 * 500
 */

package priorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxHeap {
    public static ArrayList<Integer> list = new ArrayList<>();
    public static ArrayList<Integer> listMaxValues = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            if(input.contains("Insert")) {
                String[] data = input.split(" ");
                int number = Integer.parseInt(data[1]);
                insert(number);
            } else if (input.contains("ExtractMax")) {
                if(!list.isEmpty())listMaxValues.add(extractMax());
            }
        }
        if(!listMaxValues.isEmpty()) {
            for (Integer listMaxValue : listMaxValues) {
                System.out.println(listMaxValue);
            }
        }
    }

    public static void insert(int x) {
        list.add(x);
        int i = list.size() - 1;
        int parent = (i - 1) / 2;
        while(i > 0 && list.get(parent) < list.get(i)) {
            swap(list, i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public static int extractMax() {
        int max = list.get(0);
        swap(list, 0, list.size() - 1);
        list.remove(list.size() - 1);
        sort(list);
        return max;
    }

    public static void swap (List <Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    public static void sort(List <Integer> list) {
        int index = 0;
        int left, right, parent;

        while(true) {
            parent = index;
            left = 2*index + 1;
            right = 2*index + 2;

            if(left < list.size() && list.get(left) > list.get(parent))
                parent = left;
            if(right < list.size() && list.get(right) > list.get(parent))
                parent = right;
            if(parent == index)
                break;

            swap(list, index, parent);
            index = parent;
        }
    }
}

