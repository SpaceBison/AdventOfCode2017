import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13 {
    public static void main(String... args) {
        String input = Common.getInputForDay(13);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        return Arrays.stream(input.split("\n"))
                .map(l -> Arrays.stream(l.split(": "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .filter(l -> l[0] % (2 * l[1] - 2) == 0)
                .mapToInt(l -> l[0] * l[1])
                .sum();
    }

    static int part2(String input) {
        List<int[]> layers = Arrays.stream(input.split("\n"))
                .map(l -> Arrays.stream(l.split(": "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .collect(Collectors.toList());

        return IntStream.iterate(0, i -> i + 1)
                .filter(i -> layers.stream().allMatch(l -> (i + l[0]) % (2 * l[1] - 2) != 0))
                .findFirst()
                .getAsInt();
    }
}
