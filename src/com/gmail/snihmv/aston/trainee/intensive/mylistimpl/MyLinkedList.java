package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.StringJoiner;

public class MyLinkedList<E> implements MyList<E> {
    public MyLinkedList() {
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        Node<E> head = new Node<>(element);
        if (size > 0) {
            first.prev = head;
            head.next = first;
            first = head;
        } else {
            first = last = head;
        }
        size++;
    }

    public void addLast(E element) {
        Node<E> tail = new Node<>(element);
        if (size > 0) {
            last.next = tail;
            tail.prev = last;
            last = tail;
        } else {
            first = last = tail;
        }
        size++;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void set(int index, E element) {
        checkIndex(index);
        getNodeByIndex(index).value = element;
    }


    @Override
    public void insert(int index, E element) {
        checkIndex(index);
        Node<E> inserted = new Node<>(element);
        Node<E> moved = getNodeByIndex(index);
        inserted.prev = moved.prev;
        moved.prev.next = inserted;
        moved.prev = inserted;
        inserted.next = moved;
        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).value;
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        Node<E> deleted = getNodeByIndex(index);
        removeNode(deleted);
    }

    @Override
    public boolean delete(E element) {
        Node<E> deleted = getNodeByValue(element);
        if (deleted != null) {
            removeNode(deleted);
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        int i = 0;
        for (Node<E> node = first; node != null; node = node.next) {
            res[i++] = node.value;
        }
        return res;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public void sort() {
        MyListQuickSorter.quickSort(this,null);
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        MyListQuickSorter.quickSort(this, comparator);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> node = new Node<>(null);

            {
                node.next = first;
            }

            @Override

            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public E next() {
                E res = node.next.value;
                node = node.next;
                return res;
            }
        };
    }

    private Node<E> getNodeByIndex(int index) {
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private Node<E> getNodeByValue(E value) {
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private void removeNode(Node<E> deleted) {
        if (size == 1) {
            first = last = null;
        } else if (deleted == first) {
            first = first.next;
            first.prev = null;
        } else if (deleted == last) {
            last = last.prev;
            last.next = null;
        } else {
            deleted.prev.next = deleted.next;
            deleted.next.prev = deleted.prev;
        }
        size--;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            joiner.add(node.value!=null?node.value.toString():"null");
            node = node.next;
        }
        return joiner.toString();
    }

    private void checkIndex(int index) {
        if (index >= 0 && index < size) {
            return;
        }
        throw new IllegalArgumentException("Incorrect index: " + index);
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
