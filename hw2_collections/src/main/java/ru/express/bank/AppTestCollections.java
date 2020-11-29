package ru.express.bank;

import java.util.*;

public class AppTestCollections {

    private static int COUNT = 30;

    public static void main(String[] arg) {
        DIYArrayList<Integer> src = new DIYArrayList<Integer>();
        DIYArrayList<Integer> dst = new DIYArrayList<Integer>(COUNT);

        Integer[] intSrc = new Random().ints(COUNT, 10, 99).boxed().toArray(Integer[]::new);
        Integer[] intDst = new Random().ints(COUNT, 10, 99).boxed().toArray(Integer[]::new);

        Collections.addAll(src, intSrc);
        printArray("List src:", src);

        Collections.addAll(dst, intDst);
        printArray("List dst:", dst);

        Collections.copy(src, dst);
        printArray("Array after copy dst to src:", src);

        Collections.sort(src, Comparator.naturalOrder());
        printArray("Sort src:", src);

    }

    private static void printArray(String description, List<Integer> array)
    {
        System.out.println(description);
        array.forEach(item -> System.out.print(item + " "));
        System.out.println();
    }
}
