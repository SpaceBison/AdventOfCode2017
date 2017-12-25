import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day23 {
    public static void main(String... args) {
        System.out.println(part1(Common.getInputForDay(23)));
        System.out.println(port());
    }

    static long part1(String input) {
        Coprocessor coprocessor = new Coprocessor(0, getCommands(input));

        while (coprocessor.step());

        return coprocessor.getMulCount();
    }

    static Long part2(String input) {
        LinkedList<String[]> commands = new LinkedList<>(getCommands(input));
        commands.addFirst(new String[]{"set", "a", "1"});
        Coprocessor coprocessor = new Coprocessor(0, commands);

        while (coprocessor.step()) {
            System.out.println(coprocessor.getRegisterValue("h"));
        }

        return coprocessor.getRegisterValue("h");
    }

    private static List<String[]> getCommands(String input) {
        return Arrays.stream(input.split("\n"))
                .map(line -> line.split(" "))
                .collect(Collectors.toList());
    }

    static class Coprocessor {
        private final Map<String, Long> registers = new HashMap<>();
        private final Queue<Long> inputQueue = new LinkedList<>();
        private final List<String[]> commands;
        private final long id;
        private Long snd;
        private int command = 0;
        private long mulCount = 0;

        public Coprocessor(long id, List<String[]> input) {
            commands = input;
            this.id = id;
            registers.put("p", this.id);
        }

        public long getMulCount() {
            return mulCount;
        }

        public Long getRegisterValue(String o) {
            return registers.get(o);
        }

        public void receive(long value) {
            inputQueue.add(value);
        }

        public Long send() {
            Long send = snd;
            snd = null;
            return send;
        }

        public boolean step() {
            if (command < 0 || command >= commands.size()) {
                return false;
            }

            String[] cmd = commands.get(command);

            System.out.println("\n\n");

            for (int i = 0; i < commands.size(); i++) {
                System.out.print(i + "\t" + Arrays.toString(commands.get(i)));
                if (i == command) {
                    System.out.println("\t*");
                } else {
                    System.out.println();
                }
            }

            return eval(cmd);
        }

        private boolean eval(String[] cmd) {
            switch (cmd[0]) {
                case "snd":
                    snd = getValue(cmd[1]);
                    command++;
                    return true;

                case "set":
                    registers.put(cmd[1], getValue(cmd[2]));
                    command++;
                    return true;

                case "add":
                    registers.put(cmd[1], getValue(cmd[1]) + getValue(cmd[2]));
                    command++;
                    return true;

                case "sub":
                    registers.put(cmd[1], getValue(cmd[1]) - getValue(cmd[2]));
                    command++;
                    return true;

                case "mul":
                    registers.put(cmd[1], getValue(cmd[1]) * getValue(cmd[2]));
                    command++;
                    mulCount++;
                    return true;

                case "mod":
                    long dividend = registers.get(cmd[1]);
                    long mod = dividend % getValue(cmd[2]);
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
                    long value = getValue(cmd[1]);
                    command += value > 0 ? getValue(cmd[2]) : 1;
                    return true;
                }

                case "jnz": {
                    long value = getValue(cmd[1]);
                    command += value != 0 ? getValue(cmd[2]) : 1;
                    return true;
                }
            }

            return false;
        }

        private long getValue(String expr) {
            try {
                return Long.parseLong(expr);
            } catch (NumberFormatException e) {
                return registers.getOrDefault(expr, 0L);
            }
        }
    }

    /* FAST ENOUGH :v */
    private static int port() {
        int a = 1;
        int b = 107900;
        int h = 0;

        while (true) {
            int f = 1;
            int d = 2;

            do {
                if (b % d == 0) {
                    f = 0;
                    break;
                }

                d++;
            } while (d != b);


            if (f == 0) {
                h++;
            }

            if (b == 124900) {
                return h;
            }

            b += 17;
        }
    }
}
