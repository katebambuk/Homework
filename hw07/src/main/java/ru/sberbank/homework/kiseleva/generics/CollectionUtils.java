package ru.sberbank.homework.kiseleva.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их.
 */
public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<? super T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c1.stream().anyMatch(x -> c1.contains(x) && c2.contains(x));
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List<? extends Comparable<T>> range(List<? extends Comparable<T>> list, T min, T max) {
        return list.stream()
                .filter(x -> x.compareTo(min) >= 0)
                .filter(x -> x.compareTo(max) <= 0)
                .sorted()
                .collect(Collectors.toList());

    }

    public static <T> List<? extends T> range2(List<? extends T> list, T min, T max, Comparator comparator) {
        return list.stream()
                .filter(x -> comparator.compare(x, min) >= 0)
                .filter(x -> comparator.compare(x, max) <= 0)
                .sorted()
                .collect(Collectors.toList());
    }

}
