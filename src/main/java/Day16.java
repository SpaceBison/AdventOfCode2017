import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day16 {
    public static void main(String... args) {
        String input = Common.getInputForDay(16);
        System.out.println(part1(input, 16));
        System.out.println(part2(input, 16));
    }

    static String part1(String input, int count) {
        List<Character> programs = getPrograms(count);
        doDance(input, programs);
        return getProgramsAsString(programs);
    }

    static String part2(String input, int count) {
        List<Character> programs = getPrograms(count);
        List<Character> state = programs;
        List<List<Character>> states = new ArrayList<>();
        states.add(state);

        do {
            state = singleDance(input, state);
            states.add(state);
        } while (!state.equals(programs));

        states.remove(states.size() - 1);

        List<Character> finalState = states.get(1000000000 % states.size());
        return getProgramsAsString(finalState);
    }

    static String getProgramsAsString(List<Character> afterDances) {
        return afterDances.stream().map(Object::toString).collect(Collectors.joining());
    }

    static List<Character> singleDance(String input, List<Character> programs) {
        List<Character> afterDance = new ArrayList<>(programs);
        doDance(input, afterDance);
        return afterDance;
    }

    static List<Character> getPrograms(int count) {
        return IntStream.range(0, count).mapToObj(i -> (char) ('a' + i)).collect(Collectors.toList());
    }

    static <E> void swap(List<E> list, E a, E b) {
        int indexA = list.indexOf(a);
        int indexB = list.indexOf(b);
        list.set(indexA, b);
        list.set(indexB, a);
    }

    static <E> void swap(List<E> list, int a, int b) {
        E tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }

    static <E> void spin(List<E> list, int spin) {
        int last = list.size();
        if (spin < 0) {
            spin += last;
        }
        for (int i = 0; i < spin; ++i) {
            list.add(0, list.remove(last - 1));
        }
    }

    static void doDance(String input, List<Character> programs) {
        Scanner scanner = new Scanner(input).useDelimiter("");
        do {
            String next = scanner.next();
            switch (next.charAt(0)) {
                case 's': {
                    int spin = scanner.useDelimiter(",").nextInt();
                    spin(programs, spin);
                    break;
                }

                case 'x': {
                    int a = scanner.useDelimiter("/").nextInt();
                    scanner.useDelimiter("").next();
                    int b = scanner.useDelimiter(",").nextInt();
                    swap(programs, a, b);
                    break;
                }

                case 'p': {
                    Character a = scanner.next().charAt(0);
                    scanner.next();
                    Character b = scanner.next().charAt(0);
                    swap(programs, a, b);
                    break;
                }
            }
        } while (scanner.useDelimiter("").hasNext());
    }
}
