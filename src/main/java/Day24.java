import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day24 {
    public static void main(String... args) {
        String input = Common.getInputForDay(24);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        List<List<Integer>> components = Arrays.stream(input.split("\n"))
                .map(l -> Arrays.stream(l.split("/"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return findBridges(components, 0)
                .mapToInt(b -> b.stream().mapToInt(c -> c.stream().mapToInt(i -> i).sum()).sum())
                .max()
                .getAsInt();
    }

    static int part2(String input) {
        List<List<Integer>> components = Arrays.stream(input.split("\n"))
                .map(l -> Arrays.stream(l.split("/"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return findBridges(components, 0)
                .max(Comparator.<List<List<Integer>>>comparingInt(List::size).thenComparingInt(Day24::getBridgeStrength))
                .map(Day24::getBridgeStrength)
                .get();

    }

    private static int getBridgeStrength(List<List<Integer>> b) {
        return b.stream().mapToInt(Day24::getComponentStrength).sum();
    }

    private static int getComponentStrength(List<Integer> c) {
        return c.get(0) + c.get(1);
    }

    private static Stream<List<List<Integer>>> findBridges(List<List<Integer>> components, int starting) {
        return components.stream()
                .parallel()
                .filter(c -> c.contains(starting))
                .flatMap(c -> {
                    ArrayList<List<Integer>> rest = new ArrayList<>(components);
                    rest.remove(c);
                    Integer first = c.get(0);
                    Integer second = c.get(1);
                    return Stream.concat(Stream.of(Collections.<List<Integer>>emptyList()), findBridges(rest, starting == first ? second : first))
                            .map(b -> {
                                ArrayList<List<Integer>> bridge = new ArrayList<>();
                                bridge.add(c);
                                bridge.addAll(b);
                                return bridge;
                            });
                });
    }
}
