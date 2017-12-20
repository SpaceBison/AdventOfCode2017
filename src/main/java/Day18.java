import java.util.*;
import java.util.stream.Collectors;

public class Day18 {
    public static void main(String... args) {
        String input = Common.getInputForDay(18);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static Long part1(String input) {
        Duet duet = new Duet(0, getCommands(input));

        do {
            Long send = duet.send();
            if (send != null) {
                return send;
            }
        } while (duet.step());

        return null;
    }

    private static List<String[]> getCommands(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .collect(Collectors.toList());
    }

    private static long getValue(String expr, Map<String, Long> registers) {
        try {
            return Long.parseLong(expr);
        } catch (NumberFormatException e) {
            return registers.getOrDefault(expr, 0L);
        }
    }

    static int part2(String input) {
        List<String[]> commands = getCommands(input);

        Duet p0 = new Duet(0, commands);
        Duet p1 = new Duet(1, commands);

        int p1SendCount = 0;

        do {
            Long p0send = p0.send();
            if (p0send != null) {
                p1.receive(p0send);
            }

            Long p1Send = p1.send();
            if (p1Send != null) {
                p0.receive(p1Send);
                p1SendCount++;
            }
        } while (p0.step() && p1.step());

        return p1SendCount;
    }

    static class Duet {
        private final Map<String, Long> registers = new HashMap<>();
        private final Queue<Long> inputQueue = new LinkedList<>();
        private final List<String[]> commands;
        private final long id;
        private Long snd;
        private int command = 0;

        public Duet(long id, List<String[]> input) {
            commands = input;
            this.id = id;
            registers.put("p", this.id);
        }

        public void receive(long value) {
            inputQueue.add(value);
        }

        public boolean step() {
            if (command < 0 || command >= commands.size()) {
                return false;
            }

            String[] cmd = commands.get(command);
            System.out.format("[%d]\t%2d\t%-20s\t%s\tqueue: %s\n", id, command, Arrays.toString(cmd), registers, inputQueue);
            return eval(cmd);
        }

        private boolean eval(String[] cmd) {
            switch (cmd[0]) {
                case "snd":
                    snd = getValue(cmd[1], registers);
                    command++;
                    return true;

                case "set":
                    registers.put(cmd[1], getValue(cmd[2], registers));
                    command++;
                    return true;

                case "add":
                    registers.put(cmd[1], getValue(cmd[1], registers) + getValue(cmd[2], registers));
                    command++;
                    return true;

                case "mul":
                    registers.put(cmd[1], getValue(cmd[1], registers) * getValue(cmd[2], registers));
                    command++;
                    return true;

                case "mod":
                    long dividend = registers.getOrDefault(cmd[1], 0L);
                    long mod = dividend % getValue(cmd[2], registers);
                    if (mod < 0) {
                        mod += dividend;
                    }
                    registers.put(cmd[1], mod);
                    command++;
                    return true;

                case "rcv": {
                    Long received = inputQueue.poll();
                    if (received != null) {
                        registers.put(cmd[1], received);
                        command++;
                        return true;
                    } else {
                        return false;
                    }
                }

                case "jgz": {
                    long value = registers.getOrDefault(cmd[1], 0L);
                    command += value > 0 ? getValue(cmd[2], registers) : 1;
                    return true;
                }
            }

            return false;
        }

        public Long send() {
            Long send = snd;
            snd = null;
            return send;
        }
    }
}
