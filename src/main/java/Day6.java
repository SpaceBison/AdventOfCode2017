import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String... args) {
        String input = Common.getInputForDay(6);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    /* this is a naive solution */
    public static int part1(String input) {
        List<Integer> banks = Arrays.stream(input.split("\t"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int n = banks.size();

        Set<Integer> states = new HashSet<>();
        states.add(banks.hashCode());
        do {
            int maxIndex = 0;
            int maxValue = banks.get(0);
            for (int i = 1; i < n; ++i) {
                int value = banks.get(i);
                if (value > maxValue) {
                    maxValue = value;
                    maxIndex = i;
                }
            }

            banks.set(maxIndex, 0);

            for (int j = 1; j <= maxValue; ++j) {
                int index = (maxIndex + j) % n;
                banks.set(index, banks.get(index) + 1);
            }
        }  while (states.add(banks.hashCode()));

        return states.size();
    }

    /* this is a naive solution */
    public static int part2(String input) {
        List<Integer> banks = Arrays.stream(input.split("\t"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int n = banks.size();

        List<Integer> states = new LinkedList<>();
        states.add(banks.hashCode());
        do {
            int maxIndex = 0;
            int maxValue = banks.get(0);
            for (int i = 1; i < n; ++i) {
                int value = banks.get(i);
                if (value > maxValue) {
                    maxValue = value;
                    maxIndex = i;
                }
            }

            banks.set(maxIndex, 0);

            for (int j = 1; j <= maxValue; ++j) {
                int index = (maxIndex + j) % n;
                banks.set(index, banks.get(index) + 1);
            }

            int hashCode = banks.hashCode();
            if (states.contains(hashCode)) {
                return states.size() - states.indexOf(hashCode);
            }

            states.add(hashCode);
        }  while (true);

        //return steps;
    }
}