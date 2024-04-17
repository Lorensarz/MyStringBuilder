package com.petrov;

public class Main {
    public static void main(String[] args) {
        MyStringBuilder sb = new MyStringBuilder();
        sb.append("Hello");
        System.out.println(sb.toString());

        sb.append(" World!");
        System.out.println(sb.toString());

        sb.insert(5, " Java");
        System.out.println(sb.toString());

        sb.undo();
        System.out.println(sb.toString());
    }
}
