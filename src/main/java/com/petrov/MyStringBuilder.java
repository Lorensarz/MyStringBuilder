package com.petrov;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStringBuilder {
    private char[] value;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private Deque<Memento> history;

    public MyStringBuilder() {
        value = new char[DEFAULT_CAPACITY];
        size = 0;
        history = new ArrayDeque<>();
    }

    public MyStringBuilder append(String str) {
        saveState();
        ensureCapacity(size + str.length());
        for (int i = 0; i < str.length(); i++) {
            value[size++] = str.charAt(i);
        }
        return this;
    }

    public MyStringBuilder insert(int index, String str) {
        saveState();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + str.length());
        for (int i = size - 1; i >= index; i--) {
            value[i + str.length()] = value[i];
        }
        for (int i = 0; i < str.length(); i++) {
            value[index++] = str.charAt(i);
            size++;
        }
        return this;
    }

    public MyStringBuilder undo() {
        if (!history.isEmpty()) {
            Memento memento = history.pop(); // Получаем последнее сохраненное состояние
            this.value = memento.getValue();
            this.size = memento.getSize();
        }
        return this;
    }


    private void saveState() {
        history.push(new Memento(value.clone(), size)); // Клонируем массив и сохраняем его состояние
    }

    // Приватный метод для увеличения размера внутреннего массива при необходимости
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > value.length) {
            int newCapacity = Math.max(minCapacity, value.length * 2);
            char[] newValue = new char[newCapacity];
            System.arraycopy(value, 0, newValue, 0, size);
            value = newValue;
        }
    }

    @Override
    public String toString() {
        return new String(value, 0, size);
    }

    private static class Memento {
        private final char[] value;
        private final int size;

        public Memento(char[] value, int size) {
            this.value = value;
            this.size = size;
        }

        public char[] getValue() {
            return value;
        }

        public int getSize() {
            return size;
        }
    }


}
