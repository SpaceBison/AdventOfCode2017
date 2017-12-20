import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day18 {
    public static void main(String... args) {
        String input = Common.getInputForDay(18);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static long part1(String input) {
        List<String[]> commands = Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .collect(Collectors.toList());

        Map<String, Long> registers = new HashMap<>();
        long snd = 0;

        for (int i = 0; i < commands.size() && i >= 0; ++i) {
            String[] cmd = commands.get(i);
            System.out.format("%2d\t%-20s\t%s\tsnd: %8d\n", i, Arrays.toString(cmd), registers, snd);
            switch (cmd[0]) {
                case "snd":
                    snd = getValue(cmd[1], registers);
                    break;

                case "set":
                    registers.put(cmd[1], getValue(cmd[2], registers));
                    break;

                case "add":
                    registers.put(cmd[1], getValue(cmd[1], registers) + getValue(cmd[2], registers));
                    break;

                case "mul":
                    registers.put(cmd[1], getValue(cmd[1], registers) * getValue(cmd[2], registers));
                    break;

                case "mod":
                    long dividend = registers.getOrDefault(cmd[1], 0L);
                    long mod = dividend % getValue(cmd[2], registers);
                    if (mod < 0) {
                        mod += dividend;
                    }
                    registers.put(cmd[1], mod);
                    break;

                case "rcv": {
                    long value = getValue(cmd[1], registers);
                    if (value != 0) {
                        return snd;
                    }
                    break;
                }

                case "jgz": {
                    long value = registers.getOrDefault(cmd[1], 0L);
                    if (value > 0) {
                        i += getValue(cmd[2], registers) - 1;
                    }
                }
            }
        }

        return snd;
    }

    private static long getValue(String expr, Map<String, Long> registers) {
        try {
            return Long.parseLong(expr);
        } catch (NumberFormatException e) {
            return registers.getOrDefault(expr, 0L);
        }
    }

    static int part2(String input) {
        return 0;
    }

    static class Duet {
        
    }
}
