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
        List<Integer> lengths = Arrays.stream(input.split(","))
                .map(Integer::new).collect(Collectors.toList());
        List<Byte> singleRound = getSparseHash(lengths, 256, 1);
        return singleRound.get(0) * singleRound.get(1);
    }

    static String part2(String input) {
        List<Integer> lengths = IntStream.concat(input.chars(), IntStream.of(17, 31, 73, 47, 23))
                .boxed().collect(Collectors.toList());

        List<Byte> sparseHash = getSparseHash(lengths, 256, 64);
        List<Byte> denseHash = getDenseHash(sparseHash, 16);

        return denseHash.stream().map(i -> String.format("%02x", i)).collect(Collectors.joining());
    }

    static <E> void wrappedReverseRange(List<E> list, int start, int end) {
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

    private static List<Byte> getDenseHash(List<Byte> sparseHash, int blocks) {
        int blockSize = sparseHash.size() / blocks;
        return IntStream.range(0, blocks)
                .mapToObj(block -> IntStream.range(0, blockSize)
                        .map(i -> sparseHash.get(block * blockSize + i))
                        .reduce((a, b) -> a ^ b))
                .map(OptionalInt::getAsInt)
                .map(Integer::byteValue)
                .collect(Collectors.toList());
    }

    private static List<Byte> getSparseHash(List<Integer> lengths, int size, int iterations) {
        List<Byte> list = IntStream.range(0, size).mapToObj(i -> (byte)i).collect(Collectors.toList());
        int current = 0;
        int skip = 0;

        for (int i = 0; i < iterations; ++i) {
            for (Integer length : lengths) {
                wrappedReverseRange(list, current, current + length);
                current += length + skip++;
            }
        }

        return list;
    }
}
