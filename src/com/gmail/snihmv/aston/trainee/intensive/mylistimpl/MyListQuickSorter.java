package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.Comparator;

@SuppressWarnings({"unchecked", "rawtypes"})
public class MyListQuickSorter {

    /**
     * Sorts the specified list, according to the natural ordering of its elements.
     * Uses <i>Quick sort algorithm</i>.
     * All elements in the list must implement the Comparable interface.
     * @param  list the list to be sorted.
     * @param  <E> the class of the elements in the list.
     */
    public static <E extends Comparable<? super E>> void quickSort(MyList<E> list) {
        quickSort(list, null);
    }


    public static <E> void quickSort(MyList<E> list, Comparator<? super E> c) {

        Object[] array = list.toArray();
        quickSort(array, 0, array.length - 1, c);
        for (int i = 0; i < array.length; i++) {
            list.set(i, (E) array[i]);
        }
    }


    private static void quickSort(Object[] array, int from, int to, Comparator c) {
        if (from < to) {
            int separator;
            if (c != null) {
                separator = separate(array, from, to, c);
            } else {
                separator = comparableSeparate(array, from, to);
            }
            quickSort(array, from, separator - 1, c);
            quickSort(array, separator, to, c);
        }
    }

    private static int comparableSeparate(Object[] array, int from, int to) {
        Comparable pivot = (Comparable) array[(from + to) / 2];
        int leftCursor = from;
        int rightCursor = to;
        while (leftCursor < rightCursor) {
            while (pivot.compareTo(array[leftCursor]) > 0) {
                leftCursor++;
            }
            while (pivot.compareTo(array[rightCursor]) < 0) {
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

    private static int separate(Object[] array, int from, int to, Comparator c) {
        int leftCursor = from;
        int rightCursor = to;
        Object pivot = array[(from + to) / 2];
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

    private static void swap(Object[] array, int leftCursor, int rightCursor) {
        Object tmp = array[leftCursor];
        array[leftCursor] = array[rightCursor];
        array[rightCursor] = tmp;
    }

}
