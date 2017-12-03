import java.util.Arrays;
import java.util.stream.IntStream;

public class Day2 {
    public static void main(String... args) {
        String input = Common.getInputForDay(2);
        System.out.println(part1(input));
    }

    static int part1(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> Arrays.stream(line.split("[\t ]"))
                        .mapToInt(Integer::parseInt))
                .map(IntStream::summaryStatistics)
                .mapToInt(ss -> ss.getMax() - ss.getMin())
                .sum();
    }
}
