import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day3 {
    public static void main(String... args) {
        int input = 325489;
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    public static int part1(int input) {
        double sqrt = Math.sqrt(input - 1);
        int n = (int) Math.floor(sqrt);
        int k = sqrt % 1 < 0.5 ? n : n + 1;

        int x = n % 2 == 0 ? n / (-2) : (n + 1) / 2;
        int y = k % 2 == 0 ? k / 2 : k / (-2);

        int offset = input - 1 - n * k;

        System.out.format("input: %d = %d * %d + 1 + %d corner: [%2d:%-2d]", input, n, k, offset, x, y);

        if (n != k) { // horizontal
            x = y > 0 ? x - offset : x + offset;
        } else {
            y = x >= 0 ? y + offset : y - offset;
        }

        System.out.format(" coord: [%2d:%-2d]\n", x, y);

        return Math.abs(x) + Math.abs(y);
    }

    /* This might need a cleanup */
    public static int part2(int input) {
        List<Integer> spiral = new LinkedList<>();
        spiral.addAll(Arrays.asList(0, 1, 1, 2, 4, 5, 10, 11, 23));

        for (int i = 0; i < spiral.size(); ++i) {
            Integer value = spiral.get(i);
            System.out.println("    init " + i + " " + value);
            if (value > input) {
                return value;
            }
        }

        int a = 3;
        int b = 3;
        int corner = a * b;
        int offset = 6;

        int nextValue = 0;

        int i = spiral.size();
        do {
            if (i == corner) {
                ++offset;
                nextValue = spiral.get(i - 1) + spiral.get(i - offset) + spiral.get(i - offset - 1);

                if (nextValue > input) {
                    return nextValue;
                }

                System.out.println("pre corner " + i + " " + nextValue);
                spiral.add(nextValue);
                ++i;

                ++offset;

                nextValue = nextValue + spiral.get(i - offset);
                System.out.println("    corner " + i + " " + nextValue);
                spiral.add(nextValue);
                ++i;

                if (nextValue > input) {
                    return nextValue;
                }

                nextValue = 2 * nextValue + spiral.get(i - offset);
                System.out.println("pos corner " + i + " " + nextValue);

                if (a == b) {
                    a++;
                } else {
                    b = a;
                }
                corner = a * b;
            } else {
                nextValue = nextValue + spiral.get(i - offset) + spiral.get(i - offset - 1) + spiral.get(i - offset - 2);
                System.out.println("    normal " + i + " " + nextValue);
            }

            spiral.add(nextValue);
            ++i;

            //System.out.println(spiral.subList(spiral.size() - 5, spiral.size()));
        } while (nextValue <= input);

        return nextValue;
    }
}
