import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 {
    public static void main(String... args) {
        String input = Common.getInputForDay(10);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        List<Integer> list = IntStream.range(0, 256).boxed().collect(Collectors.toList());
        List<Integer> parsedInput = Arrays.stream(input.split(",")).map(Integer::new).collect(Collectors.toList());

        int current = 0;
        int skip = 0;
        for (Integer length : parsedInput) {
            reverseRange(list, current, current + length);
            current += length + skip++;
        }

        return list.get(0) * list.get(1);
    }

    static String part2(String input) {
        List<Integer> list = IntStream.range(0, 256).boxed().collect(Collectors.toList());
        List<Integer> parsedInput = input.chars().boxed().collect(Collectors.toList());
        parsedInput.addAll(Arrays.asList(17, 31, 73, 47, 23));

        int current = 0;
        int skip = 0;
        for (int i = 0; i < 64; ++i) {
            for (Integer length : parsedInput) {
                reverseRange(list, current, current + length);
                current += length + skip++;
            }
        }

        return IntStream.range(0, 16)
                .mapToObj(chunk -> IntStream.range(0, 16)
                        .map(i -> list.get(chunk * 16 + i))
                        .reduce((a, b) -> a ^ b))
                .map(OptionalInt::getAsInt)
                .map(i -> String.format("%02x", i))
                .collect(Collectors.joining());
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
}
