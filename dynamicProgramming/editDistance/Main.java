package dynamicProgramming.editDistance;
/** Задача на программирование: расстояние редактирования
 * Вычислите расстояние редактирования двух данных непустых строк длины не более 10^2, содержащих строчные буквы латинского алфавита.
 * Sample Input 1:
 * ab
 * ab
 * Sample Output 1:
 * 0
 * Sample Input 2:
 * short
 * ports
 * Sample Output 2:
 * 3
  */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        System.out.println(estimateEditDistance(str1, str2));
    }

    private static int estimateEditDistance(String str1, String str2) {
        int arraySize;
        int rep;
        String top;
        String side;
        if(str1.length() <= str2.length()) {
            top = str1;
            side = str2;
        }
        else {
            top = str2;
            side = str1;
        }
        arraySize = top.length() + 1;
        rep = side.length() + 1;

        int[] prev = new int[arraySize];
        int[] curr;

        for (int i = 0; i < prev.length; i++) {
            prev[i] = i;
        }

        for (int i = 1; i < rep; i++) {
            curr = new int[arraySize];
            curr[0] = i;
            for (int j = 1; j < prev.length; j++) {
                if(top.charAt(j-1) == side.charAt(i-1))
                    curr[j] = prev[j-1];
                else
                    curr[j] = Math.min(Math.min(prev[j-1], prev[j]), curr[j-1]) + 1;
            }
            prev = curr;
        }
        return prev[prev.length-1];
    }
}
