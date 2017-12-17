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
        List<Character> programs = IntStream.range(0, count).mapToObj(i -> (char) ('a' + i)).collect(Collectors.toList());
        doDance(input, programs);
        return programs.stream().map(Object::toString).collect(Collectors.joining());
    }

    static String part2(String input, int count) {
        List<Character> programs = IntStream.range(0, count).mapToObj(i -> (char) ('a' + i)).collect(Collectors.toList());
        List<Character> tmpPrograms = new ArrayList<>(programs);
        List<Character> afterDance = new ArrayList<>(programs);
        doDance(input, afterDance);

        List<Integer> permutation = afterDance.stream()
                .map(programs::indexOf)
                .collect(Collectors.toList());

        List<Integer> order = IntStream.range(0, 16).boxed().collect(Collectors.toList());
        List<Integer> perm = new ArrayList<>(order);

        List<List<Integer>> permutations = new LinkedList<>();
        do {
            perm = applyPermutation(perm, permutation);
            permutations.add(perm);
            System.out.println(perm);
        } while (!order.equals(perm));

        permutations.add(0, permutations.remove(permutations.size() - 1));

        List<Integer> finalPermutation = permutations.get(1_000_000_000 % permutations.size());

        System.out.println(programs);
        programs = applyPermutation(programs, finalPermutation);
        System.out.println(programs);
        return programs.stream().map(Object::toString).collect(Collectors.joining());
    }

    private static <E> List<E> applyPermutation(List<E> list, List<Integer> permutation) {
        List<E> tmp = new ArrayList<>(16);
        for (int j = 0; j < 16; ++j) {
            tmp.add(j, list.get(permutation.get(j)));
        }
        return tmp;
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

    private static void doDance(String input, List<Character> programs) {
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
