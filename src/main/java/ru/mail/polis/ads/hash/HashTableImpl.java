package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private ArrayList<ArrayList<Pair>> array;
    private int currentCapacity;
    private int size;

    private class Pair {
        private final Key key;
        private final Value value;

        private Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableImpl() {
        currentCapacity = 16;
        array = new ArrayList<>();
        for (int i = 0; i < currentCapacity; i++) {
            array.add(new ArrayList<>());
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        ArrayList<Pair> pairs = array.get(hash(key.hashCode()) & (currentCapacity - 1));
        for (Pair pair : pairs) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        ArrayList<Pair> list = array.get(hash(key.hashCode()) & (currentCapacity - 1));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                array.get(hash(key.hashCode()) & (currentCapacity - 1)).set(i, new Pair(key, value));
                return;
            }
        }
        array.get(hash(key.hashCode()) & (currentCapacity - 1)).add(new Pair(key, value));
        size++;
        resize();
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        ArrayList<Pair> list = array.get(hash(key.hashCode()) & (currentCapacity - 1));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                Value value = list.get(i).value;
                array.get(hash(key.hashCode()) & (currentCapacity - 1)).remove(i);
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        double loadFactor = (double) size / currentCapacity;
        if (loadFactor <= 0.75) {
            return;
        }
        currentCapacity >>= 1;
        ArrayList<ArrayList<Pair>> newArray = new ArrayList<>(currentCapacity);
        for (int i = 0; i < currentCapacity; i++) {
            newArray.add(new ArrayList<>());
        }
        for (ArrayList<Pair> pairs : array) {
            for (Pair pair : pairs) {
                newArray.get(hash(pair.key.hashCode()) & (currentCapacity - 1)).add(pair);
            }
        }
        array = newArray;
    }

    private int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }
}
