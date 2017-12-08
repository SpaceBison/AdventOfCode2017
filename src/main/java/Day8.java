import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String... args) {
        String input = Common.getInputForDay(8);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        List<String[]> tokenLines = Arrays.stream(input.split("\n"))
                .map(s -> s.split(" "))
                .collect(Collectors.toList());

        Map<String, Integer> registers = new HashMap<>();

        for (String[] tokenLine : tokenLines) {
            String reg = tokenLine[0];
            String op = tokenLine[1];
            int amount = Integer.parseInt(tokenLine[2]);
            int condRegValue = registers.getOrDefault(tokenLine[4], 0);
            String condRel = tokenLine[5];
            int condValue = Integer.parseInt(tokenLine[6]);
            int regValue = registers.getOrDefault(reg, 0);

            boolean cond = false;
            switch (condRel) {
                case "<": cond = condRegValue < condValue; break;
                case "<=": cond = condRegValue <= condValue; break;
                case "==": cond = condRegValue == condValue; break;
                case ">": cond = condRegValue > condValue; break;
                case ">=": cond = condRegValue >= condValue; break;
                case "!=": cond = condRegValue != condValue; break;
            }

            if (cond) {
                switch (op) {
                    case "inc": regValue += amount; break;
                    case "dec": regValue -= amount; break;
                }
            }

            registers.put(reg, regValue);
        }

        return registers.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    static int part2(String input) {
        List<String[]> tokenLines = Arrays.stream(input.split("\n"))
                .map(s -> s.split(" "))
                .collect(Collectors.toList());

        Map<String, Integer> registers = new HashMap<>();

        int maxValue = Integer.MIN_VALUE;

        for (String[] tokenLine : tokenLines) {
            String reg = tokenLine[0];
            String op = tokenLine[1];
            int amount = Integer.parseInt(tokenLine[2]);
            int condRegValue = registers.getOrDefault(tokenLine[4], 0);
            String condRel = tokenLine[5];
            int condValue = Integer.parseInt(tokenLine[6]);
            int regValue = registers.getOrDefault(reg, 0);

            boolean cond = false;
            switch (condRel) {
                case "<": cond = condRegValue < condValue; break;
                case "<=": cond = condRegValue <= condValue; break;
                case "==": cond = condRegValue == condValue; break;
                case ">": cond = condRegValue > condValue; break;
                case ">=": cond = condRegValue >= condValue; break;
                case "!=": cond = condRegValue != condValue; break;
            }

            if (cond) {
                switch (op) {
                    case "inc": regValue += amount; break;
                    case "dec": regValue -= amount; break;
                }
            }

            registers.put(reg, regValue);
            maxValue = Math.max(maxValue, registers.values().stream().mapToInt(i -> i).max().getAsInt());
        }

        return maxValue;
    }
}