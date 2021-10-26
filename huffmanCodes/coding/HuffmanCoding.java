/** кодирование Хаффмана
 * По данной непустой строке s длины не более 10000, состоящей из строчных букв латинского алфавита,
 * постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв k,
 * встречающихся в строке, и размер получившейся закодированной строки.
 * В следующих k строках запишите коды букв в формате "letter: code".
 * В последней строке выведите закодированную строку.
 * Sample Input 1:
 * a
 * Sample Output 1:
 * 1 1
 * a: 0
 * 0
 * Sample Input 2:
 * abacabad
 * Sample Output 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 */
package huffmanCodes.coding;

import java.util.*;

public class HuffmanCoding {
    static HashMap<Character, Integer> map = new HashMap<>();
    static HashMap<Character, String> table = new HashMap<>();
    static String str;
    static PriorityQueue<Node> priorityQueue;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();

        for (char c : str.toCharArray()) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else map.put(c, 1);
        }

        printInfo(createTree());
    }

    public static int createTree() {
        priorityQueue = new PriorityQueue<>();
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            priorityQueue.add(new LeafNode(entry.getKey(), entry.getValue()));
        }
        int sum = 0;
        if(priorityQueue.size() > 1) {
            sum = 0;
            while(priorityQueue.size() > 1) {
                Node first = priorityQueue.poll();
                Node second = priorityQueue.poll();
                JointNode jointNode = new JointNode(first, second);
                sum += jointNode.sum;
                priorityQueue.add(jointNode);
            }
            Node node = priorityQueue.poll();
            node.buildCode("");
        }
        else {
            sum = str.length();
            Node node = priorityQueue.poll();
            node.buildCode("0");
        }
        return sum;
    }

    public static void printInfo(int sum) {
        System.out.println(table.size() + " " + sum);
        for (Map.Entry<Character, String> entry:
                table.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        for (char c : str.toCharArray()) {
            System.out.print(table.get(c));
        }
    }
}

class Node implements Comparable<Node> {
    final int sum;
    String code;

    void buildCode(String code) {
        this.code = code;
    }
    public Node(int sum) {
        this.sum = sum;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(sum, o.sum);
    }
}

class JointNode extends Node {
    Node left;
    Node right;

    @Override
    void buildCode(String code) {
        super.buildCode(code);
        left.buildCode(code + "0");
        right.buildCode(code + "1");
    }

    public JointNode(Node left, Node right) {
        super(left.sum + right.sum);
        this.left = left;
        this.right = right;
    }
}

class LeafNode extends Node {
    char ch;

    @Override
    void buildCode(String code) {
        super.buildCode(code);
        HuffmanCoding.table.put(ch, code);
    }

    public LeafNode(char ch, int count) {
        super(count);
        this.ch = ch;
    }
}
