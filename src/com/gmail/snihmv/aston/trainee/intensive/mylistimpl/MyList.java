package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;


import java.util.Comparator;

public interface MyList<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    void add(E element);

    void set(int index, E element);

    void insert(int index, E element);

    E get(int index);

    void delete(int index);

    boolean delete(E element);

    E[] toArray();

    void clear();

    void sort(Comparator<? super E> comparator);
}
