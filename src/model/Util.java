package model;

import java.util.*;

public class Util {
    public static <T> Map<T, Integer> flatListToAmountMap(Collection<T> list) {
        HashMap<T, Integer> amounts = new HashMap<>();
        for (T item : list) {
            int amount = amounts.getOrDefault(item, 0);
            amounts.put(item, amount + 1);
        }
        return amounts;
    }

    public static <T> LinkedList<T> amountMapToFlatList(Map<T, Integer> amounts) {
        LinkedList<T> list = new LinkedList<>();
        amounts.forEach((item, amount) -> {
            for (int i = 0; i < amount; i++) {
                list.add(item);
            }
        });
        return list;
    }

    // https://stackoverflow.com/a/10305419
    public static <E> List<List<E>> generatePerm(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    public static double sum(Collection<Double> vals) {
        return vals.stream().mapToDouble(Double::new).sum();
    }
}
