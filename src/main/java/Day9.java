import java.util.LinkedList;
import java.util.Queue;

public class Day9 {
    public static void main(String... args) {
        String input = Common.getInputForDay(9);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        Queue<Character> chars = input.chars().mapToObj(i -> (char) i).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        return parseGroup(chars, 1);
    }

    private static int parseGroup(Queue<Character> chars, int depth) {
        //System.out.println("looking at group: " + chars);
        int score = depth;
        Character openingBracket = chars.poll();
        if (openingBracket != '{') throw new IllegalStateException();

        while (true) {
            switch (chars.peek()) {
                case '{':
                    score += parseGroup(chars, depth + 1);
                    break;

                case '<':
                    parseGarbage(chars);
                    break;

                case '}':
                    chars.poll();
                    return score;

                default:
                    chars.poll();
                    break;
            }
        }
    }

    private static void parseGarbage(Queue<Character> chars) {
        //System.out.println("looking at garbage: " + chars);
        Character openingBracket = chars.poll();
        if (openingBracket != '<') throw new IllegalStateException();

        while (true) {
            switch (chars.poll()) {
                case '!':
                    chars.poll();
                    break;

                case '>':
                    return;
            }
        }
    }

    static int part2(String input) {
        return 0;
    }
}
