package quickSort;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PointsEntry {
    static int[] startPoints;
    static int[] endPoints;
    static int[] points;
    static int[] sortedPoints;

    static Map<Integer, Integer> mapStart = new LinkedHashMap<>();
    static Map<Integer, Integer> mapEnd = new LinkedHashMap<>();
    static Map<Integer, Integer> counterMap = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        int nSegments;
        int nPoints;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] infoLine = reader.readLine().split(" ");
            nSegments = Integer.parseInt(infoLine[0]);
            nPoints = Integer.parseInt(infoLine[1]);

            points = new int[nPoints];
            startPoints = new int[nSegments];
            endPoints = new int[nSegments];

            for (int i = 0; i < nSegments; i++) {
                String[] seg = reader.readLine().split(" ");
                startPoints[i] = Integer.parseInt(seg[0]);
                endPoints[i]  = Integer.parseInt(seg[1]);
            }

            String[] p = reader.readLine().split(" ");
            for (int i = 0; i < nPoints; i++) {
                points[i] = Integer.parseInt(p[i]);
            }
        }
        sortedPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            sortedPoints[i] = points[i];
            counterMap.put(points[i], 0);
        }
        //points = new int[]{8, 6, 2, 7, 12};
        /*startPoints = new int[50000];
        endPoints = new int[50000];
        points = new int[50000];

        for (int i = 0; i < 50000; i++) {
            startPoints[i] = (int)(Math.random()*99_999_999);
            endPoints[i] = (int)(Math.random()*99_999_999);
            points[i] = (int)(Math.random()*99_999_999);
        }
        sortedPoints = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            sortedPoints[i] = points[i];
            counterMap.put(points[i], 0);
        }*/
        quickSort(startPoints, 0, startPoints.length - 1);
        quickSort(endPoints, 0, endPoints.length - 1);
        quickSort(sortedPoints, 0, sortedPoints.length - 1);

        //long a = System.currentTimeMillis();
        pointsSearching();
        //long b = System.currentTimeMillis();
        //System.out.println("TIME: " + (b-a));

    }
    public static void pointsSearching () {
        int startIndex = 0;
        int endIndex = 0;

        int[] counts1 = new int[sortedPoints.length];
        int[] counts2 = new int[sortedPoints.length];

        for (int i = 0; i < sortedPoints.length; i++) {
            if(startIndex > startPoints.length - 1) startIndex = startPoints.length;
            else startIndex = searchBeginPoints(startPoints, sortedPoints[i], startIndex);
            counts1[i] = startIndex;
        }
        System.out.println(Arrays.toString(counts1));
        for (int j = 0; j < sortedPoints.length; j++) {
            if(endIndex > startPoints.length - 1) endIndex = startPoints.length;
            else endIndex = searchEndPoints(endPoints, sortedPoints[j], endIndex);
            counts2[j] = endIndex;
        }
        System.out.println(Arrays.toString(counts2));
//        for (int j = sortedPoints.length - 1; j >= 0 ; j--) {
//            if(endIndex < 0) endIndex = 0;
//            else endIndex = searchEndPoints(endPoints, sortedPoints[j], endIndex);
//            counts2[j] = endIndex;
//        }
        //System.out.println(Arrays.toString(counts2));
        for (int i = 0; i < counts1.length; i++) {
            counterMap.put(sortedPoints[i], counts1[i] - counts2[i]);
        }
        //System.out.println(counterMap);
        for (Integer value:
                counterMap.values()) {
            System.out.print(value + " ");
        }
    }

    public static int searchBeginPoints(int[] array, int number, int begin) {
        int left = begin;
        int right = array.length - 1;
        int middle = 0;

        while (left <= right) {

            middle = (left + right) / 2;

            if(number == array[middle]) {
                middle++;
                while(middle <= array.length - 1) {
                   if(number != array[middle])
                       break;
                   else middle++;

                }
                break;
            }
            else if(number < array[middle]) right = middle - 1;
            else if(number > array[middle]) {
                left = middle + 1;
                middle++;
            }
        }
        return middle;
    }

    public static int searchEndPoints(int[] array, int number, int end) {
        int left = end;
        int right = array.length - 1;
        int middle = 0;

        while (left <= right) {

            middle = (left + right) / 2;

            if(number == array[middle]) {
                while(middle != 0) {
                    middle--;
                    if(number != array[middle]) {
                        middle++;
                        break;
                    }

                }

                break;
            }
            else if(number < array[middle]) right = middle - 1;
            else if(number > array[middle]) {
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
        //int pivot = array[(leftBorder + rightBorder) / 2];
        int l = leftBorder;
        int r = rightBorder;

        while(l <= r) {

            while(array[l] < pivot)
                l++;
            while (array[r] > pivot)
                r--;

            if(l <= r) {
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
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


