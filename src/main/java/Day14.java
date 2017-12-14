import java.math.BigDecimal;
import java.math.BigInteger;
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
        return 0;
    }
}
