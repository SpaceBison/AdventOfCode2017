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
        Map<String, Set<String>> graph = parseGraph(input);
        Set<String> zero = getGroup(graph, "0");
        return zero.size();
    }

    private static Map<String, Set<String>> parseGraph(String input) {
        return Arrays.stream(input.split("\n"))
                    .map(l -> l.split(" <-> "))
                    .collect(Collectors.toMap(a -> a[0], a -> Arrays.stream(a[1].split(", ")).collect(Collectors.toSet())));
    }

    private static Set<String> getGroup(Map<String, Set<String>> graph, String startingNode) {
        Set<String> group = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add(startingNode);

        while (group.addAll(current)) {
            current = current.stream()
                    .flatMap(n -> graph.getOrDefault(n, Collections.emptySet()).stream())
                    .collect(Collectors.toSet());
        }
        return group;
    }

    static int part2(String input) {
        Map<String, Set<String>> graph = parseGraph(input);
        Set<String> ungrouped = new HashSet<>(graph.keySet());
        int groups = 0;

        while (!ungrouped.isEmpty()) {
            String node = ungrouped.iterator().next();
            ungrouped.removeAll(getGroup(graph, node));
            ++groups;
        }

        return groups;
    }
}