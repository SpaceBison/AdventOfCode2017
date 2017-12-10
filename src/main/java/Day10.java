import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 {
    public static void main(String... args) {
        String input = Common.getInputForDay(10);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        return part1Impl(
                IntStream.range(0, 256).boxed().collect(Collectors.toList()),
                Arrays.stream(input.split(",")).map(Integer::new).collect(Collectors.toList()));
    }

    static int part1Impl(List<Integer> list, List<Integer> lengths) {
        int current = 0;
        int skip = 0;
        for (Integer length : lengths) {
            reverseRange(list, current, current + length);
            current += length + skip++;
        }

        return list.get(0) * list.get(1);
    }

    static <E> void reverseRange(List<E> list, int start, int end) {
        int swaps = (end - start) / 2;
        int size = list.size();
        for (int i = 0; i < swaps; ++i) {
            int first = (start + i) % size;
            int second = (end - i - 1) % size;
            E tmp = list.get(first);
            list.set(first, list.get(second));
            list.set(second, tmp);
        }
    }

    static int part2(String input) {
        return 0;
    }
}
