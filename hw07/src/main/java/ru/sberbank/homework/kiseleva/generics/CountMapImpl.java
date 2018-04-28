package ru.sberbank.homework.kiseleva.generics;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Ekaterina Kiseleva on 02.03.2018.
 */
public class CountMapImpl<T> implements CountMap<T> {
    private Map<Integer, T> map = new TreeMap<>();
    private static int key = 0;


    @Override
    public void add(T t) {
        map.put(key++, t);
    }

    @Override
    public int getCount(T t) {
        return (int) map.values().stream()
                .filter(x -> x.equals(t))
                .count();
    }

    @Override
    public int remove(T t) {
        int in = getCount(t);
        map.remove(t);
        return in;
    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    public Map<? super T, Integer> toMap() {
        return new TreeMap<>();
    }

    @Override
    public void addAll(CountMap<T> source) {
        map.putAll((Map<? extends Integer, ? extends T>) source);
    }

    @Override
    public void toMap(Map destination) {
        destination.putAll(map);
    }
}
