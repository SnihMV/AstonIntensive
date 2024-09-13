package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.UUID;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<>();
        fillMyListByStrings(list, 11);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(list.contains("foo"));
        System.out.println(list.delete("buz"));
        list.insert(7,"foo");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.contains("foo"));
        System.out.println(list.delete("foo"));
        System.out.println(list);
    }

    private static void fillMyListByStrings(MyList<String> list, int count) {
        Stream.generate(UUID::randomUUID)
                .limit(count)
                .map(UUID::toString)
                .map(s -> s.substring(30))
                .forEach(list::add);
    }
}
