package quickSort;

/** Задача на программирование: точки и отрезки
 * В первой строке задано два целых числа 1 ≤ n ≤ 50000 и 1 ≤ m ≤ 50000 — количество отрезков и точек на прямой, соответственно.
 * Следующие n строк содержат по два целых числа — координаты концов отрезков.
 * Последняя строка содержит mm целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю.
 * Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
 * Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.
 * Sample Input:
 * 2 3
 * 0 5
 * 7 10
 * 1 6 11
 * Sample Output:
 * 1 0 0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PointsEntry {
    static int[] startPoints;
    static int[] endPoints;

    static Map<Integer, Integer> mapStart = new LinkedHashMap<>();
    static Map<Integer, Integer> mapEnd = new LinkedHashMap<>();

    static SegmentsPoints[] beginPointsSegment;
    static SegmentsPoints[] endPointsSegment;
    static Points[] points;

    public static void main(String[] args) throws IOException {
        int nSegments;
        int nPoints;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] infoLine = reader.readLine().split(" ");
            nSegments = Integer.parseInt(infoLine[0]);
            nPoints = Integer.parseInt(infoLine[1]);

            startPoints = new int[nSegments];
            endPoints = new int[nSegments];

            for (int i = 0; i < nSegments; i++) {
                String[] seg = reader.readLine().split(" ");
                startPoints[i] = Integer.parseInt(seg[0]);
                endPoints[i]  = Integer.parseInt(seg[1]);
            }

            points = new Points[nPoints];
            String[] p = reader.readLine().split(" ");
            for (int i = 0; i < nPoints; i++) {
                points[i] = new Points(i, Integer.parseInt(p[i]));
            }
        }

        quickSort(startPoints, 0, startPoints.length - 1);
        quickSort(endPoints, 0, endPoints.length - 1);
        Arrays.sort(points, new Comparator<Points>() {
             @Override
            public int compare(Points o1, Points o2) {
                return o1.getValue() - o2.getValue();
            }
         });
        //System.out.println("Sorted points: " + Arrays.toString(points));
        for (int i = 0; i < nSegments; i++) {
            if(mapStart.containsKey(startPoints[i]))
                mapStart.put(startPoints[i], mapStart.get(startPoints[i]) + 1);
            else
               mapStart.put(startPoints[i], 1);

            if(mapEnd.containsKey(endPoints[i]))
                mapEnd.put(endPoints[i], mapEnd.get(endPoints[i]) + 1);
            else
                mapEnd.put(endPoints[i], 1);
        }

        beginPointsSegment = new SegmentsPoints[mapStart.size()];
        endPointsSegment = new SegmentsPoints[mapEnd.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry:
             mapStart.entrySet()) {
            beginPointsSegment[i++] = new SegmentsPoints(entry.getKey(), entry.getValue());
        }

        i = 0;
        for (Map.Entry<Integer, Integer> entry:
                mapEnd.entrySet()) {
            endPointsSegment[i++] = new SegmentsPoints(entry.getKey(), entry.getValue());
        }
        //long a = System.currentTimeMillis();
        pointsSearching();
        //long b = System.currentTimeMillis();
        //System.out.println("TIME: " + (b-a));

    }
    public static void pointsSearching () {
        int indexStartingPoints = 0;
        int indexEndingPoints = 0;

        int prevIndexStartingPoints = 0;
        int prevCountStartingPoints = 0;

        int prevIndexEndingPoints = 0;
        int prevCountEndingPoints = 0;

        //System.out.println(Arrays.toString(beginPointsSegment));
        for (int i = 0; i < points.length; i++) {
            if(indexStartingPoints > beginPointsSegment.length - 1) indexStartingPoints = beginPointsSegment.length;
            else indexStartingPoints = searchPartOfPoints(beginPointsSegment, points[i], indexStartingPoints, true);

            if(indexEndingPoints > endPointsSegment.length - 1) indexEndingPoints = endPointsSegment.length;
            else indexEndingPoints = searchPartOfPoints(endPointsSegment, points[i], indexEndingPoints, false);

            for (int j = prevIndexStartingPoints; j < indexStartingPoints; j++) {
                prevCountStartingPoints += beginPointsSegment[j].getRep();
            }
            prevIndexStartingPoints = indexStartingPoints;

            for (int j = prevIndexEndingPoints; j < indexEndingPoints; j++) {
                prevCountEndingPoints += endPointsSegment[j].getRep();
            }
            prevIndexEndingPoints = indexEndingPoints;
            points[i].setEntry(prevCountStartingPoints - prevCountEndingPoints);
        }
        Arrays.sort(points, new Comparator<Points>() {
            @Override
            public int compare(Points o1, Points o2) {
                return o1.getId() - o2.getId();
            }
        });
        for (Points point : points) {
            System.out.print(point.getEntry() + " ");
        }
    }

    public static int searchPartOfPoints(SegmentsPoints[] array, Points point, int begin, boolean isBeginPoints) {
        int left = begin;
        int right = array.length - 1;
        int middle = 0;

        while (left <= right) {

            middle = (left + right) / 2;

            if(point.getValue() == array[middle].getValue()) {
                if(isBeginPoints) middle++;
                break;
            }
            else if(point.getValue()  < array[middle].getValue()) right = middle - 1;
            else if(point.getValue()  > array[middle].getValue()) {
                left = middle + 1;
                middle++;
            }
        }
        return middle;
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    static void quickSort(int[] array, int leftBorder, int rightBorder) {
        if (array.length == 0) return;
        if(leftBorder >= rightBorder) return;
        int[] tempArray = new int[rightBorder - leftBorder + 1];
        for (int i = leftBorder; i < rightBorder + 1; i++) {
            tempArray[i - leftBorder] = array[i];
        }
        int pivot = getRandom(tempArray);
        int l = leftBorder;
        int r = rightBorder;

        while(l <= r) {

            while(array[l] < pivot)
                l++;
            while (array[r] > pivot)
                r--;

            if(l <= r) {
                swap(array, l, r);
                l++;
                r--;
            }
        }

        if(l < rightBorder)
            quickSort(array, l, rightBorder);
        if(r > leftBorder)
            quickSort(array, leftBorder, r);
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
