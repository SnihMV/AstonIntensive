package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;


import java.util.Comparator;

public interface MyList<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(E element);

    void add(E element);

    void set(int index, E element);

    void insert(int index, E element);

    E get(int index);

    void delete(int index);

    boolean delete(E element);

    Object[] toArray();

    void clear();

    void sort();

    void sort(Comparator<? super E> comparator);
}
