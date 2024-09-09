package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.Comparator;

public class MyListQuickSorter {

    public static <E extends Comparable<? super E>> void quickSort(MyList<E> list) {
        quickSort(list, Comparator.naturalOrder());
    }

    public static <E> void quickSort(MyList<E> list, Comparator<? super E> c) {

        E[] array = list.toArray();
        quickSort(array, 0, array.length - 1, c);
        for (int i = 0; i < array.length; i++) {
            list.set(i, array[i]);
        }
    }


    private static <E> void quickSort(E[] array, int from, int to, Comparator<? super E> c) {
        if (from < to) {
            int separator = separate(array, from, to, c);
            quickSort(array, from, separator - 1, c);
            quickSort(array, separator, to, c);
        }
    }

    private static <E> int separate(E[] array, int from, int to, Comparator<? super E> c) {
        int leftCursor = from;
        int rightCursor = to;
        E pivot = array[(from + to) / 2];
        while (leftCursor < rightCursor) {
            while (c.compare(array[leftCursor], pivot) < 0) {
                leftCursor++;
            }
            while (c.compare(array[rightCursor], pivot) > 0) {
                rightCursor--;
            }
            if (leftCursor <= rightCursor) {
                swap(array, leftCursor, rightCursor);
                leftCursor++;
                rightCursor--;
            }
        }
        return leftCursor;
    }

    private static <E> void swap(E[] array, int leftCursor, int rightCursor) {
        E tmp = array[leftCursor];
        array[leftCursor] = array[rightCursor];
        array[rightCursor] = tmp;
    }

}
