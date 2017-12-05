import java.util.Arrays;
import java.util.HashSet;

public class Day4 {
    public static void main(String... args) {
        String input = Common.getInputForDay(4);
        System.out.println(part1(input));
    }

    public static long part1(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .filter(words -> new HashSet<>(Arrays.asList(words)).size() == words.length)
                .count();
    }
}
