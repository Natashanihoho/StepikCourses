/* Покрыть отрезки точками
* По данным nn отрезкам необходимо найти множество точек минимального размера,
* для которого каждый из отрезков содержит хотя бы одну из точек.
* В первой строке дано число n
* Каждая из последующих n строк содержит по два числа, задающих начало и конец отрезка.
* Выведите оптимальное число m точек и сами m точек
* Если таких множеств точек несколько, выведите любое из них.
* Sample Input 1:
3
1 3
2 5
3 6
Sample Output 1:
1
3
Sample Input 2:
4
4 7
1 3
2 5
5 6
Sample Output 2:
2
3 6
 */

package coveringSegments;

import java.util.*;

public class CoveringSegments {
    static ArrayList<Point> pointsList = new ArrayList<>();
    static Map<Integer, Boolean> map = new HashMap<>();
    static ArrayList<Integer> foundPoints = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nSegments = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < nSegments; i++) {
            String[] separated = scanner.nextLine().split(" ");
            pointsList.add(new Point(Integer.parseInt(separated[0]), "L", i+1));
            pointsList.add(new Point(Integer.parseInt(separated[1]), "R", i+1));
        }

        findPoints();
        printInfo();
    }

    static void findPoints() {
        Collections.sort(pointsList, (o1, o2) -> o1.compareTo(o2));

        for (Point point:
                pointsList) {
            if(point.getEdge().equals("L")) {
                map.put(point.getSegmentNumber(), false);
            }
            else if(point.getEdge().equals("R")) {
                if(map.get(point.getSegmentNumber()) == false) {
                    foundPoints.add(point.getX());
                    for (Map.Entry<Integer, Boolean> entry:
                            map.entrySet()) {
                        map.put(entry.getKey(), true);
                    }
                }
            }
        }
    }

    static void printInfo() {
        System.out.println(foundPoints.size());
        for (Integer point:
                foundPoints) {
            System.out.print(point + " ");
        }
    }
}
