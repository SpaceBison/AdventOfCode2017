import java.util.Arrays;
import java.util.stream.IntStream;

public class Day2 {
    public static void main(String... args) {
        String input = Common.getInputForDay(2);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> Arrays.stream(line.split("[\t ]"))
                        .mapToInt(Integer::parseInt)
                        .summaryStatistics())
                .mapToInt(ss -> ss.getMax() - ss.getMin())
                .sum();
    }

    static int part2(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> Arrays.stream(line.split("[\t ]"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .flatMapToInt(arr -> IntStream.range(0, arr.length)
                        .flatMap(i -> IntStream.range(0, arr.length)
                                .filter(j -> j != i)
                                .mapToDouble(j -> (double) arr[i] / arr[j])
                                .filter(d -> d == Math.floor(d))
                                .mapToInt(d -> (int) d)))
                .sum();
    }
}
