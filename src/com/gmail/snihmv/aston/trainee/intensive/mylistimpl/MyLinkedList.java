package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.Comparator;
import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {
    public MyLinkedList() {

    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E element) {
        if (isEmpty()) {
            head = tail = new Node<>(element);
        } else {
            Node<E> newTail = new Node<>(element);
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public void insert(int index, E element) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void delete(int index) {

    }

    @Override
    public boolean delete(E element) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public void clear() {

    }

    @Override
    public void sort() {

    }

    @Override
    public void sort(Comparator<? super E> comparator) {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element) {
            this.value = element;
        }
    }
}
