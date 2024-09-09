package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.StringJoiner;

/**
 * An array-based data structure that provides random access to contained elements.
 * It is similar to {@link java.util.ArrayList ArrayList} and was created for educational purpose only
 * to understand how resizable-array collection works.
 * Implements {@code MyList} interface by analogue with Collection API hierarchy.
 * <p>The {@code get}, {@code set}, and {@code clear} operations run in constant time.</p>
 * <p>The {@code add}, {@code insert} and {@code delete} operations run in linear time O(n).</p>
 *
 * @param <E> the type of the contained elements
 * @author Mike Snikhovskiy
 * @see MyList
 * @see java.util.List List
 * @see java.util.ArrayList ArrayList
 */

public class MyArrayList<E> implements MyList<E>, RandomAccess {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;
    private Object[] array;
    private int size;

    /**
     * Constructs an empty list with a default initial capacity value.
     */
    public MyArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) throw new IllegalArgumentException("Initial capacity must not be negative");
        this.array = new Object[initialCapacity];
    }

    /**
     * Returns the number of the actual elements contained in this list
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns if the list is effectively empty
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element element to be added to this list
     */
    @Override
    public void add(E element) {
        checkCapacity();
        array[size++] = element;
    }

    /**
     * Adds the specified {@code element} at the specified {@code index} in this list
     * instead of the existed element.
     * <p><strong>Note that the replaced element is unavoidable lost.</strong></p>
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @throws IllegalArgumentException if the index is out of range
     *                                  ({@code index < 0 || index >= size()})
     */
    @Override
    public void set(int index, E element) {
        checkIndex(index);
        array[index] = element;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Existed subsequence starting at specified index is shifted to the right.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IllegalArgumentException if the index is out of range
     *                                  ({@code index < 0 || index >= size()})
     */
    @Override
    public void insert(int index, E element) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified {@code index} in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified index in this list
     * @throws IllegalArgumentException if the index is out of range
     *                                  ({@code index < 0 || index >= size()})
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) array[index];
    }

    /**
     * Removes the element at the specified {@code index} in this list
     * and shifts subsequent elements starting at {@code index + 1} to the left
     * taking place of removed element, subtracting one from their indices if it is present.
     *
     * @param index the index of the element to be removed
     * @throws IllegalArgumentException if the index is out of range
     *                                  ({@code index < 0 || index >= size()})
     */
    @Override
    public void delete(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.
     *
     * @param element element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public boolean delete(E element) {
        int index = getIndexOf(element);
        if (index >= 0) {
            delete(index);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(array, size);
    }

    /**
     * Makes the list is effectively empty
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Sorts this list according to the order induced by the specified {@link Comparator}.
     * If the specified comparator is {@code null} then elements' type {@code E} must
     * implement the {@link Comparable} interface and the list is sorted by natural ordering.
     *
     * @param comparator the Comparator used to compare list elements.
     *                   A null value indicates that the elements' natural ordering should be used.
     * @throws ClassCastException if the specified {@code comparator} is null and
     *                            the elements' type {@code E} does not implement {@link Comparable} interface.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<? super E> comparator) {
        Arrays.sort((E[]) array, 0, size, comparator);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p><strong>The returned iterator is not <i>fail-fast</i></strong>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int pointer;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) array[pointer++];
            }
        };
    }

    /**
     * Returns a string representation of this collection.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * ({@code "[]"}).  Adjacent elements are separated by the characters
     * {@code ", "} (comma and space).
     *
     * @return a string representation of this collection
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(array[i].toString());
        }
        return joiner.toString()/* + " size:" + size + " capacity:" + array.length*/;
    }

    private int getIndexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) return i;
        }
        return -1;
    }

    private void checkCapacity() {
        if (size == array.length) {
            int capacity = array.length << 1;
            array = Arrays.copyOf(array, capacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Incorrect index: " + index);
    }

}
