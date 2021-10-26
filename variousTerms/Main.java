/** Различные слагаемые
 * По данному числу n найдите максимальное число k, для которого n можно представить как сумму k различных натуральных слагаемых.
 * Выведите в первой строке число k, во второй — k слагаемых.
 * Sample Input 1:
 * 4
 * Sample Output 1:
 * 2
 * 1 3
 * Sample Input 2: *
 * 6
 * Sample Output 2: *
 * 3
 * 1 2 3
 */

package variousTerms;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        printInfo(getTerms(n));

    }

    public static ArrayList<Integer> getTerms(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 1;
        int result = 0;

        while(result != n) {
            result += i;

            if(result <= n) {
                list.add(i);
                i++;
            }
            else {
                i = list.get(list.size() - 1) + 1;
                list.remove(list.size()-1);
                if(list.isEmpty()) result = 0;
                else result = list.stream().mapToInt(a -> a).sum();
            }
        }
        return list;
    }

    public static void printInfo(ArrayList<Integer> list) {
        System.out.println(list.size());

        for (Integer number:
             list) {
            System.out.print(number + " ");
        }
    }
}
