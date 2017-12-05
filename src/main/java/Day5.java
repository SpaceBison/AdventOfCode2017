import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String... args) {
        String input = Common.getInputForDay(5);
        System.out.println(part1(input));
        //System.out.println(part2(input));
    }

    public static int part1(String input) {
        List<Integer> jumps = Arrays.stream(input.split("\n"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        int index = 0;
        int steps = 0;
        while (0 <= index && index < jumps.size()) {
            int jump = jumps.get(index);
            jumps.set(index, jump + 1);
            index += jump;
            ++steps;
        }
        return steps;
    }
}
