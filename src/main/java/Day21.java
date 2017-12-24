import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day21 {
    public static void main(String... args) {
        String input = Common.getInputForDay(21);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        Pattern pattern = iterate(input, 5);
        return pattern.countBits();
    }

    static int part2(String input) {
        Pattern pattern = iterate(input, 18);
        return pattern.countBits();
    }

    static Pattern iterate(String input, int iterations) {
        Map<Pattern, Pattern> rules = Arrays.stream(input.split("\n"))
                .map(Day21::parseRule)
                .collect(Collectors.toMap(r -> r.get(0), r -> r.get(1)));

        Map<Pattern, Pattern> fullRules = getFullRules(rules);

        Pattern pattern = Pattern.HACKER;

        List<Pattern> patternKeys = new ArrayList<>(fullRules.keySet());
        patternKeys.sort(Comparator.comparingInt(Pattern::getSize));

        for (int i = 0; i < iterations; ++i) {
            for (Pattern key : patternKeys) {
                int size = key.getSize();
                if (pattern.getSize() % size == 0) {
                    List<Pattern> divided = pattern.divide(size);
                    List<Pattern> replaced = divided.stream()
                            .map(fullRules::get)
                            .collect(Collectors.toList());
                    pattern = Pattern.merge(replaced);
                    break;
                }
            }
        }
        return pattern;
    }

    private static HashMap<Pattern, Pattern> getFullRules(Map<Pattern, Pattern> rules) {
        HashMap<Pattern, Pattern> fullMap = new HashMap<>();
        for (Map.Entry<Pattern, Pattern> entry : rules.entrySet()) {
            Set<Pattern> variants = entry.getKey().getVariants();
            for (Pattern variant : variants) {
                fullMap.put(variant, entry.getValue());
            }
        }
        return fullMap;
    }

    private static List<Pattern> parseRule(String rule) {
        return Arrays.stream(rule.split(" => "))
                .map(Pattern::parse)
                .collect(Collectors.toList());
    }

    public static class Pattern {
        public static final Pattern HACKER = Pattern.parse(".#./" + "..#/" + "###");
        private final int mSize;
        private final Set<Point> mPoints = new HashSet<>();

        public Pattern(int size, Collection<Point> points) {
            mSize = size;
            mPoints.addAll(points);
        }

        public static Pattern parse(String pattern) {
            HashSet<Point> points = new HashSet<>();
            String[] split = pattern.split("/");
            for (int y = 0; y < split.length; ++y) {
                for (int x = 0; x < split.length; ++x) {
                    if (split[y].charAt(x) == '#') {
                        points.add(new Point(x, y));
                    }
                }
            }
            return new Pattern(split.length, points);
        }

        public int getSize() {
            return mSize;
        }

        public List<Pattern> divide(int newSize) {
            int div = mSize / newSize;
            int parts = div * div;
            List<Set<Point>> subPoints = IntStream.range(0, parts).mapToObj(i -> new HashSet<Point>()).collect(Collectors.toList());

            mPoints.forEach(point -> {
                int row = point.y / newSize;
                int col = point.x / newSize;
                subPoints.get(div * row + col).add(new Point(point.x % newSize, point.y % newSize));
            });

            return subPoints.stream()
                    .map(ps -> new Pattern(newSize, ps))
                    .collect(Collectors.toList());
        }

        public static Pattern merge(List<Pattern> patterns) {
            int patternSize = patterns.get(0).getSize();
            int div = (int) Math.sqrt(patterns.size());

            HashSet<Point> points = new HashSet<>();
            for (int row = 0; row < div; ++row) {
                for (int col = 0; col < div; ++col) {
                    int finalCol = col;
                    int finalRow = row;
                    points.addAll(patterns.get(div * row + col).mPoints.stream()
                            .map(p -> new Point(p.x + finalCol * patternSize, p.y + finalRow * patternSize))
                            .collect(Collectors.toSet()));
                }
            }

            return new Pattern(patternSize * div, points);
        }

        public Set<Pattern> getVariants() {
            HashSet<Pattern> patterns = new HashSet<>(8);
            patterns.add(this);
            patterns.add(this.flippedHorizontally());
            patterns.add(this.flippedVertically());

            Pattern once = rotated();
            patterns.add(once);
            patterns.add(once.flippedVertically());
            patterns.add(once.flippedHorizontally());

            Pattern twice = once.rotated();
            patterns.add(twice);
            patterns.add(twice.flippedVertically());
            patterns.add(twice.flippedHorizontally());

            Pattern thrice = twice.rotated();
            patterns.add(thrice);
            patterns.add(thrice.flippedVertically());
            patterns.add(thrice.flippedHorizontally());

            return patterns;
        }

        public Pattern flippedHorizontally() {
            return new Pattern(mSize, mPoints.stream().map(p -> new Point(mSize - 1 - p.x, p.y)).collect(Collectors.toSet()));
        }

        public Pattern flippedVertically() {
            return new Pattern(mSize, mPoints.stream().map(p -> new Point(p.x, mSize - 1 - p.y)).collect(Collectors.toSet()));
        }

        public Pattern rotated() {
            return new Pattern(mSize, mPoints.stream().map(p -> new Point(mSize - 1 - p.y, p.x)).collect(Collectors.toSet()));
        }

        public int countBits() {
            return mPoints.size();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < mSize; ++y) {
                for (int x = 0; x < mSize; ++x) {
                    sb.append(mPoints.contains(new Point(x, y)) ? '#' : '.');
                }
                sb.append('\n');
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pattern pattern = (Pattern) o;

            if (mSize != pattern.mSize) return false;
            return mPoints.equals(pattern.mPoints);
        }

        @Override
        public int hashCode() {
            int result = mSize;
            result = 31 * result + mPoints.hashCode();
            return result;
        }
    }
}
