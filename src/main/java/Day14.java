import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14 {
    public static void main(String... args) {
        String input = "ffayrhll";
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        return IntStream.range(0, 128)
                .mapToObj(i -> input + '-' + i)
                .map(Day10::part2)
                .map(h -> new BigInteger(h, 16))
                .mapToInt(BigInteger::bitCount)
                .sum();
    }

    static int part2(String input) {
        List<Integer> parts = IntStream.range(0, 128)
                .mapToObj(i -> input + '-' + i)
                .map(Day10::part2)
                .map(h -> new BigInteger(h, 16))
                .flatMap(bi -> String.format("%128s", bi.toString(2))
                        .replace(' ', '0')
                        .chars()
                        .map(i -> i - '0')
                        .boxed())
                .collect(Collectors.toList());


        return parts.size();
    }
}
