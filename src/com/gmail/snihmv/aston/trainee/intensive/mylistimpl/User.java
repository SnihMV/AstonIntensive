package com.gmail.snihmv.aston.trainee.intensive.mylistimpl;

public record User(String name, int age) implements Comparable<User> {

    @Override
    public int compareTo(User o) {
        return this.name().compareTo(o.name());
    }
}
