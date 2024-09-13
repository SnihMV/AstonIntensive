package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private static final String DUMMY = "Marina";
    MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
        list.add("Mike");
        list.add("Victoria");
        list.add("Bob");
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        list.clear();
        assertSize(0);
    }

    @Test
    void contains() {
        assertTrue(list.contains("Bob"));
        assertFalse(list.contains(DUMMY));
    }

    @Test
    void add() {
        list.add(DUMMY);
        assertTrue(list.contains(DUMMY));
        assertSize(4);
    }

    @Test
    void set() {
        assertThrows(IllegalArgumentException.class, () -> list.set(-1, DUMMY));
        assertThrows(IllegalArgumentException.class, () -> list.set(3, DUMMY));
        assertDoesNotThrow(() -> list.set(1, DUMMY));
        assertGet(1, DUMMY);
    }

    @Test
    void insert() {
        assertThrows(IllegalArgumentException.class, () -> list.insert(-1, DUMMY));
        assertThrows(IllegalArgumentException.class, () -> list.insert(3, DUMMY));
        assertDoesNotThrow(() -> list.insert(1, DUMMY));
        assertSize(4);
        assertGet(1, DUMMY);
    }

    @Test
    void get() {
        assertThrows(IllegalArgumentException.class, () -> list.get(-1));
        assertThrows(IllegalArgumentException.class, () -> list.get(3));
        assertGet(2, "Bob");
    }

    @Test
    void deleteByIndex() {
        assertThrows(IllegalArgumentException.class, () -> list.delete(-1));
        assertThrows(IllegalArgumentException.class, () -> list.delete(3));
        assertDoesNotThrow(() -> list.delete(2));
        assertFalse(list.contains("Bob"));
        assertSize(2);
    }

    @Test
    void deleteByValue() {
        assertFalse(list.delete(DUMMY));
        assertSize(3);
        assertTrue(list.delete("Victoria"));
        assertFalse(list.contains("Victoria"));
        assertSize(2);
    }

    @Test
    void sortByNaturalOrder() {
        list.sort();
        assertIterableEquals(List.of("Bob", "Mike", "Victoria"), list);
    }

    @Test
    void sortWithComparator() {
        list.sort(Comparator.reverseOrder());
        assertIterableEquals(List.of("Victoria", "Mike", "Bob"), list);
    }

    private void assertSize(int size) {
        assertEquals(size, list.size());
    }

    private void assertGet(int index, String expected) {
        assertEquals(expected, list.get(index));
    }
}