package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        MyList<User> users = new MyArrayList<>();
        users.add(new User("Mike", 42));
        users.add(new User("Fedor", 24));
        users.add(new User("Sofia", 24));
        users.add(new User("Bob", 57));
        users.add(new User("Vlad", 21));
        users.add(new User("Julia", 37));
        users.add(new User("Boris", 42));
        System.out.println(users);
        MyListQuickSorter.quickSort(users);
        System.out.println(users);
        MyListQuickSorter.quickSort(users, Comparator.comparing(User::age).thenComparing(User::name));
        System.out.println(users);
        users.sort(Comparator.reverseOrder());
        for (User user : users) {
            System.out.println(user);
        }
        users.sort();
        System.out.println(users);

    }

}
