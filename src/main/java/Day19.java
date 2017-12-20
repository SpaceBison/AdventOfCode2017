import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day19 {
    public static void main(String... args) {
        String input = Common.getInputForDay(19);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static String part1(String input) {
        List<List<Character>> map = getMap(input);
        List<Character> route = getRoute(map);
        return route.stream().filter(Character::isAlphabetic).map(Object::toString).collect(Collectors.joining());
    }

    static int part2(String input) {
        List<List<Character>> map = getMap(input);
        List<Character> route = getRoute(map);
        return route.size();
    }

    private static List<List<Character>> getMap(String input) {
        return Arrays.stream(input.split("\n"))
                    .map(l -> l.chars()
                            .mapToObj(i -> ((char) i))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
    }

    private static List<Character> getRoute(List<List<Character>> map) {
        int currentX = 0;
        int currentY = 0;
        Direction direction = null;

        List<Character> firstLine = map.get(0);

        for (int i = 0; i < firstLine.size(); ++i) {
            if (firstLine.get(i) != ' ') {
                currentX = i;
                currentY = 0;
                direction = Direction.DOWN;
                break;
            }
        }

        List<Character> route = new LinkedList<>();
        char c = get(map, currentX, currentY);
        do {
            route.add(c);
            if (c == '+') {
                switch (direction) {
                    case LEFT:
                    case RIGHT:
                        if (get(map, currentX, currentY + 1) != ' ') {
                            direction = Direction.DOWN;
                        } else if (get(map, currentX, currentY - 1) != ' ') {
                            direction = Direction.UP;
                        }
                        break;

                    case UP:
                    case DOWN:
                        if (get(map, currentX + 1, currentY) != ' ') {
                            direction = Direction.RIGHT;
                        } else if (get(map, currentX - 1, currentY) != ' ') {
                            direction = Direction.LEFT;
                        }
                        break;
                }
            }

            switch (direction) {
                case UP:
                    currentY--;
                    break;
                case DOWN:
                    currentY++;
                    break;
                case LEFT:
                    currentX--;
                    break;
                case RIGHT:
                    currentX++;
                    break;
            }

            c = get(map, currentX, currentY);
        } while (c != ' ');

        return route;
    }

    private static char get(List<List<Character>> map, int currentX, int currentY) {
        if (currentY >= map.size()) return ' ';
        List<Character> line = map.get(currentY);
        return currentX >= line.size() ? ' ' : line.get(currentX);
    }

    private static char get(String input, int width, int x, int y) {
        return input.charAt(y * width + x);
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
