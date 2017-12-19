import java.util.LinkedList;

public class Day17 {
    public static void main(String... args) {
        int input = 337;
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(int input) {
        LinkedList<Integer> buffer = new LinkedList<>();
        buffer.add(0);

        int current = 0;
        for (int i = 0; i < 2017; ++i) {
            current = (current + input) % buffer.size() + 1;
            buffer.add(current, i + 1);
        }

        return buffer.get(current + 1);
    }

    static int part2(int input) {
        int current = 0;
        int afterZero = -1;
        for (int i = 1; i <= 50000000; ++i) {
            current = (current + input) % i + 1;

            if (current == 1) {
                afterZero = i;
            }
        }

        return afterZero;
    }
}
