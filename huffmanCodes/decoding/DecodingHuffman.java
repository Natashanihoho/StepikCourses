/** декодирование Хаффмана
 * Восстановите строку по её коду и беспрефиксному коду символов.
 * В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв,
 * встречающихся в строке, и размер получившейся закодированной строки, соответственно.
 * В следующих k строках записаны коды букв в формате "letter: code".
 * Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
 * В качестве букв могут встречаться лишь строчные буквы латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз.
 * Наконец, в последней строке записана закодированная строка. Исходная строка и коды всех букв непусты.
 * Заданный код таков, что закодированная строка имеет минимальный возможный размер.
 * В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита.
 * Sample Input 1:
 * 1 1
 * a: 0
 * 0
 * Sample Output 1:
 * a
 * Sample Input 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 * Sample Output 2:
 * abacabad
 */
package huffmanCodes.decoding;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DecodingHuffman {
    static Map<String, Character> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstStr = scanner.nextLine().split(" ");
        int symbols = Integer.parseInt(firstStr[0]);
        int codeSize = Integer.parseInt(firstStr[1]);
        for (int i = 0; i < symbols; i++) {
            String[] str = scanner.nextLine().split(": ");
            map.put(str[1], str[0].charAt(0));
        }
        char[] code = scanner.nextLine().toCharArray();
        String part = "";
        for (int i = 0; i < codeSize; i++) {
            part += code[i];
            if(map.containsKey(part)) {
                System.out.print(map.get(part));
                part = "";
            }
        }
    }
}
