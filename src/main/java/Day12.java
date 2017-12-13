import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String... args) {
        String input = Common.getInputForDay(12);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        Map<String, Set<String>> graph = Arrays.stream(input.split("\n"))
                .map(l -> l.split(" <-> "))
                .collect(Collectors.toMap(a -> a[0], a -> Arrays.stream(a[1].split(", ")).collect(Collectors.toSet())));

        Set<String> zero = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("0");

        while (zero.addAll(current)) {
            current = current.stream()
                    .flatMap(n -> graph.getOrDefault(n, Collections.emptySet()).stream())
                    .collect(Collectors.toSet());
            System.out.println("current: " + current);
            System.out.println("zero: " + current);
        }

        return zero.size();
    }

    static int part2(String input) {
        return 0;
    }
}