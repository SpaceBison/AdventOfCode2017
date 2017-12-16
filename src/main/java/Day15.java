import java.util.PrimitiveIterator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day15 {
    public static void main(String... args) {
        int startA = 679;
        int startB = 771;
        System.out.println(part1(startA, startB));
        System.out.println(part2(startA, startB));
    }

    static long part1(int startA, int startB) {
        PrimitiveIterator.OfLong generatorA = getGenerator(startA, 16807, 1);
        PrimitiveIterator.OfLong generatorB = getGenerator(startB, 48271, 1);

        return Stream.generate(() -> new long[]{generatorA.nextLong(), generatorB.nextLong()})
                .limit(40_000_000)
                .filter(ll -> ((ll[0] ^ ll[1]) & 0xffff) == 0)
                .count();
    }

    static long part2(int startA, int startB) {
        PrimitiveIterator.OfLong generatorA = getGenerator(startA, 16807, 4);
        PrimitiveIterator.OfLong generatorB = getGenerator(startB, 48271, 8);

        return Stream.generate(() -> new long[]{generatorA.nextLong(), generatorB.nextLong()})
                .limit(5_000_000)
                .filter(ll -> ((ll[0] ^ ll[1]) & 0xffff) == 0)
                .count();
    }

    private static PrimitiveIterator.OfLong getGenerator(long startingValue, long factor, long criterium) {
        return LongStream.iterate(startingValue, prev -> (prev * factor) % 2147483647)
                .skip(1)
                .filter(l -> l % criterium == 0)
                .iterator();
    }

}
