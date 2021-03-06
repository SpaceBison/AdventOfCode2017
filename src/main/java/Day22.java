import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Day22 {
    public static void main(String... args) {
        String input = Common.getInputForDay(22);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int iterate(Set<Point> infected, int iterations) {
        Point carrier = new Point();

        int infectingBursts = 0;

        Direction direction = Direction.UP;
        for (int i = 0; i < iterations; ++i) {
            if (infected.contains(carrier)) {
                infected.remove(carrier);
                direction = direction.turnRight();
            } else {
                infected.add(new Point(carrier));
                direction = direction.turnLeft();
                infectingBursts++;
            }

            switch (direction) {
                case UP:
                    carrier.y--;
                    break;
                case RIGHT:
                    carrier.x++;
                    break;
                case DOWN:
                    carrier.y++;
                    break;
                case LEFT:
                    carrier.x--;
                    break;
            }
        }
        return infectingBursts;
    }

    static int iterate2(Set<Point> infected, int iterations) {
        Point carrier = new Point();

        int infectingBursts = 0;

        Set<Point> weakened = new HashSet<>();
        Set<Point> flagged = new HashSet<>();

        Direction direction = Direction.UP;
        for (int i = 0; i < iterations; ++i) {
            if (infected.contains(carrier)) {
                infected.remove(carrier);
                flagged.add(new Point(carrier));
                direction = direction.turnRight();
            } else if (weakened.contains(carrier)) {
                weakened.remove(carrier);
                infected.add(new Point(carrier));
                infectingBursts++;
            } else if (flagged.contains(carrier)) {
                flagged.remove(carrier);
                direction = direction.turnRight().turnRight();
            } else { // clean
                weakened.add(new Point(carrier));
                direction = direction.turnLeft();
            }

            switch (direction) {
                case UP:
                    carrier.y--;
                    break;
                case RIGHT:
                    carrier.x++;
                    break;
                case DOWN:
                    carrier.y++;
                    break;
                case LEFT:
                    carrier.x--;
                    break;
            }
        }
        return infectingBursts;
    }

    static Set<Point> parseInput(String input) {
        Set<Point> infected = new HashSet<>();
        String[] split = input.split("\n");

        int centerY = split.length / 2;
        int centerX = split[0].length() / 2;

        for (int y = 0; y < split.length; ++y) {
            for (int x = 0; x < split.length; ++x) {
                if (split[y].charAt(x) == '#') {
                    infected.add(new Point(x - centerX, y - centerY));
                }
            }
        }
        return infected;
    }

    static int part1(String input) {
        Set<Point> infected = parseInput(input);
        return iterate(infected, 10000);
    }

    static int part2(String input) {
        Set<Point> infected = parseInput(input);
        return iterate2(infected, 10000000);
    }

    private static String toString(Collection<Point> infected, Collection<Point> weakened, Collection<Point> flagged, Point current) {
        Rectangle rectangle = new Rectangle();
        infected.forEach(rectangle::add);
        rectangle.add(current);

        StringBuilder sb = new StringBuilder();

        for (int y = (int) rectangle.getMinY(); y <= rectangle.getMaxY(); ++y) {
            for (int x = (int) rectangle.getMinX(); x <= rectangle.getMaxX(); ++x) {
                Point point = new Point(x, y);
                if (infected.contains(point)) {
                    sb.append(point.equals(current) ? 'X' : '#');
                } else if (weakened.contains(point)) {
                    sb.append(point.equals(current) ? 'W' : 'w');
                } else if (flagged.contains(point)) {
                    sb.append(point.equals(current) ? 'F' : 'f');
                } else {
                    sb.append(point.equals(current) ? 'x' : '.');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    private enum Direction {
        UP, RIGHT, DOWN, LEFT;

        public Direction turnLeft() {
            Direction[] values = values();
            return values[(ordinal() + values.length - 1) % values.length];
        }

        public Direction turnRight() {
            Direction[] values = values();
            return values[(ordinal() + 1) % values.length];
        }
    }
}
