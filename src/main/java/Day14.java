import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Integer> squares = IntStream.range(0, 128)
                .mapToObj(i -> input + '-' + i)
                .map(Day10::part2)
                .map(h -> new BigInteger(h, 16))
                .flatMap(bi -> String.format("%128s", bi.toString(2))
                        .replace(' ', '0')
                        .chars()
                        .map(i -> i - '0')
                        .boxed())
                .collect(Collectors.toList());

        int ones = 0;
        for (int i = 0; i < squares.size(); ++i) {
            if (squares.get(i) > 0) {
                squares.set(i, ++ones);
            }
        }

        Map<Integer, Integer> groups = new HashMap<>(ones + 1);
        for (int i = 0; i <= ones; ++i) {
            groups.put(i, i);
        }

        for (int row = 0; row < 128; ++row) {
            for (int col = 0; col < 128; ++col) {
                int current = 128 * row + col;

                if (squares.get(current) != 0) {
                    if (col > 0) {
                        int leftSquare = squares.get(current - 1);
                        if (leftSquare != 0) {
                            int leftGroup = getGroup(groups, leftSquare);
                            int currentSquare = squares.get(current);
                            squares.set(current, leftGroup);
                            groups.put(currentSquare, leftGroup);
                        }
                    }

                    int up = current - 128;
                    if (up >= 0) {
                        int upSquare = squares.get(up);
                        if (upSquare != 0) {
                            int currentSquare = squares.get(current);
                            int upGroup = getGroup(groups, upSquare);
                            groups.put(currentSquare, upGroup);
                        }
                    }
                }
            }
        }

        squares = squares.stream()
                .map(s -> getGroup(groups, s))
                .collect(Collectors.toList());

        return (int) squares.stream().distinct().count() - 1;
    }

    private static int getGroup(Map<Integer, Integer> groups, int square) {
        Integer group = groups.get(square);
        while (square != group) {
            square = group;
            group = groups.get(square);
        }
        return group;
    }
}
