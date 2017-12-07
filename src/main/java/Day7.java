import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    static String part2(String input) {
        return null;
    }
}
