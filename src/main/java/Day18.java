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

    static int part1(String input) {
        List<String[]> commands = Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .collect(Collectors.toList());

        Map<String, Integer> registers = new HashMap<>();
        int lastPlayed = -1;

        for (int i = 0; i < commands.size(); ++i) {
            String[] cmd = commands.get(i);
            System.out.println(registers);
            System.out.println(Arrays.toString(cmd));
            switch (cmd[0]) {
                case "snd":
                    lastPlayed = getValue(cmd[1], registers);
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
                    int divident = getValue(cmd[1], registers);
                    int mod = divident % getValue(cmd[2], registers);
                    if (mod < 0) {
                        mod += divident;
                    }
                    registers.put(cmd[1], mod);
                    break;

                case "rcv": {
                    int value = getValue(cmd[1], registers);
                    if (value != 0) {
                        return lastPlayed;
                    }
                    break;
                }

                case "jgz": {
                    int value = registers.getOrDefault(cmd[1], 0);
                    if (value > 0) {
                        i += getValue(cmd[2], registers) - 1;
                    }
                }
            }
        }

        return lastPlayed;
    }

    private static int getValue(String expr, Map<String, Integer> registers) {
        try {
            return Integer.parseInt(expr);
        } catch (NumberFormatException e) {
            return registers.getOrDefault(expr, 0);
        }
    }

    static int part2(String input) {
        return 0;
    }

    static class Duet {

    }
}
