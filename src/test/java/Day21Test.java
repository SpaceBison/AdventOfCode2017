import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day21Test {
    private static final String INPUT = "../.# => ##./#../...\n" +
            ".#./..#/### => #..#/..../..../#..#";

    @Test
    public void part1() throws Exception {
        assertThat(Day21.part1(INPUT), is(12));
    }

    @Test
    public void part2() throws Exception {
    }

    @Test
    public void pattern() {
        Day21.Pattern.HACKER.getVariants();
    }

    @Test
    public void divide() {
        Day21.Pattern pattern = Day21.Pattern.parse("#.../..#./.#../...#");
        System.out.println(pattern);
        List<Day21.Pattern> divide = pattern.divide(2);
        Day21.Pattern merged = Day21.Pattern.merge(divide);
        assertThat(merged, is(pattern));
    }
}