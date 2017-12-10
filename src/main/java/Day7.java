
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String... args) {
        String input = Common.getInputForDay(7);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static String part1(String input) {
        List<String> nonLeafLines = Arrays.stream(input.split("\n"))
                .filter(s -> s.contains("->"))
                .collect(Collectors.toList());

        HashSet<String> nonLeaves = new HashSet<>();
        HashSet<String> children = new HashSet<>();
        for (String line : nonLeafLines) {
            String[] split = line.split(" \\(\\d*\\) -> ");
            nonLeaves.add(split[0]);
            children.addAll(Arrays.asList(split[1].split(", ")));
        }

        nonLeaves.removeAll(children);

        return nonLeaves.iterator().next();
    }

    static int part2(String input) {
        Set<String[]> parsed = Arrays.stream(input.split("\n"))
                .map(l -> l.split("( \\()|(\\) -> )|\\)"))
                .collect(Collectors.toSet());

        Map<String, Integer> weights = parsed.stream().collect(Collectors.toMap(
                a -> a[0],
                a -> Integer.parseInt(a[1])));

        HashMap<String, Integer> originalWeights = new HashMap<>(weights);

        Map<String, Set<String>> children = parsed.stream().collect(Collectors.toMap(
                a -> a[0],
                a -> a.length == 2 ? Collections.emptySet() : Arrays.stream(a[2].split(", ")).collect(Collectors.toSet())));

        while (true) {
            for (Map.Entry<String, Set<String>> entry : children.entrySet()) {
                String key = entry.getKey();
                Set<String> value = entry.getValue();

                if (!value.isEmpty() && value.stream().map(children::get).allMatch(Set::isEmpty)) { // only leaves

                    if (value.stream().map(weights::get).distinct().count() != 1) { // one of them does not match
                        Map<Integer, Set<String>> disksByWeight = value.stream().collect(Collectors.groupingBy(weights::get, Collectors.toSet()));
                        Map.Entry<Integer, Set<String>> badDisk = disksByWeight.entrySet().stream().filter(e -> e.getValue().size() == 1).findFirst().get();

                        String badDiskName = badDisk.getValue().iterator().next();
                        Integer badWeight = badDisk.getKey();

                        disksByWeight.remove(badWeight);
                        Integer goodWeight = disksByWeight.keySet().iterator().next();

                        Integer originalBadWeight = originalWeights.get(badDiskName);

                        return originalBadWeight - (badWeight - goodWeight);
                    } else {
                        weights.put(key, weights.get(key) + value.stream().mapToInt(weights::get).sum());
                        children.put(key, Collections.emptySet());
                    }
                }
            }
        }
    }
}
