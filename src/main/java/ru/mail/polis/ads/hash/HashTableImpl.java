package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private ArrayList<Pair> bucketArray;
    private int currentCapacity;
    private int size;

    private class Pair {
        private Pair next;
        private Value value;
        private final Key key;

        private Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableImpl() {
        currentCapacity = 16;
        bucketArray = new ArrayList<>();
        for (int i = 0; i < currentCapacity; i++) {
            bucketArray.add(null);
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Pair head = bucketArray.get(getHash(key));
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int bucketIndex = getHash(key);
        Pair head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucketArray.get(bucketIndex);
        Pair newPair = new Pair(key, value);
        newPair.next = head;
        bucketArray.set(bucketIndex, newPair);
        if ((double) size / currentCapacity >= 0.75) {
            ArrayList<Pair> temp = bucketArray;
            bucketArray = new ArrayList<>();
            currentCapacity *= 2;
            size = 0;
            for (int i = 0; i < currentCapacity; i++) {
                bucketArray.add(null);
            }
            for (Pair pair : temp) {
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
        Pair head = bucketArray.get(getHash(key));
        Pair prev = null;
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
            bucketArray.set(getHash(key), head.next);
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
        return key.hashCode() % currentCapacity;
    }
}
