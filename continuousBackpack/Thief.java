/* Непрерывный рюкзак
* Первая строка содержит количество предметов и вместимость рюкзака
* Каждая из следующих nn строк задаёт стоимость и объём предмета
* Выведите максимальную стоимость частей предметов
* (от каждого предмета можно отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
* помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
* Sample Input:
  3 50
  60 20
  100 50
  120 30
  Sample Output:
  180.000
*/
package continuousBackpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;

public class Thief {
    static ArrayList<Thing> things = new ArrayList<>();
    private static double total = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstLine = scanner.nextLine().split(" ");
        int amountThings = Integer.parseInt(firstLine[0]);
        Backpack backpack = new Backpack(Integer.parseInt(firstLine[1]));

        for (int i = 0; i < amountThings; i++) {
            String[] sep = scanner.nextLine().split(" ");
            things.add(new Thing(Integer.parseInt(sep[0]), Integer.parseInt(sep[1])));
        }

        Collections.sort(things, ((o1, o2) -> Double.compare(o2.getPriceForOne(), o1.getPriceForOne())));

        for (int i = 0; i < things.size(); i++) {
            if(putObject(backpack, things.get(i))) break;
        }
        Formatter formatter = new Formatter();
        formatter.format("%.3f", getTotal());
        System.out.printf(formatter.toString());
    }

    public static boolean putObject (Backpack backpack, Thing thing) {
        int takenVolume = thing.getVolume();

        if(thing.getVolume() > backpack.getFreeSpace()) {
            takenVolume = backpack.getFreeSpace();
        }
        total += takenVolume * thing.getPriceForOne();
        backpack.setFreeSpace(backpack.getFreeSpace() - takenVolume);
        return backpack.getFreeSpace() == 0? true: false;
    }

    public static double getTotal() {
        return total;
    }
}
