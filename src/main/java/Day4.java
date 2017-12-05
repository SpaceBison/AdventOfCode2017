import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String... args) {
        String input = Common.getInputForDay(4);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    public static long part1(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .filter(words -> new HashSet<>(Arrays.asList(words)).size() == words.length)
                .count();
    }

    public static long part2(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .filter(words -> Arrays.stream(words)
                        .map(word -> word.chars()
                                .sorted()
                                .boxed()
                                .collect(Collectors.toList()))
                        .distinct()
                        .count() == words.length)
                .count();
    }
}
