package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private Pair<Key, Value>[] bucketArray;
    private int currentCapacity;
    private int size;

    private static class Pair<Key, Value> {
        private Pair<Key, Value> next;
        private Value value;
        private final Key key;

        private Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTableImpl() {
        currentCapacity = 16;
        bucketArray = new Pair[currentCapacity];
        for (int i = 0; i < currentCapacity; i++) {
            bucketArray[i] = null;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Pair<Key, Value> head = bucketArray[getHash(key)];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int bucketIndex = getHash(key);
        Pair<Key, Value> head = bucketArray[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucketArray[bucketIndex];
        Pair<Key, Value> newPair = new Pair<>(key, value);
        newPair.next = head;
        bucketArray[bucketIndex] = newPair;
        if ((double) size / currentCapacity >= 0.75) {
            Pair<Key, Value>[] temp = bucketArray;
            currentCapacity *= 2;
            bucketArray = new Pair[currentCapacity];
            size = 0;
            for (int i = 0; i < currentCapacity; i++) {
                bucketArray[i] = null;
            }
            for (Pair<Key, Value> pair : temp) {
                while (pair != null) {
                    put(pair.key, pair.value);
                    pair = pair.next;
                }
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Pair<Key, Value> head = bucketArray[getHash(key)];
        Pair<Key, Value> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            bucketArray[getHash(key)] = head.next;
        }
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getHash(Object key) {
        return Math.abs(key.hashCode() % currentCapacity);
    }
}
