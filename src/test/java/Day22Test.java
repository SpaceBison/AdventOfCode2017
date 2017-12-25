import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day22Test {
    private static final String INPUT = "..#\n" +
            "#..\n" +
            "...";

    @Test
    public void part1() throws Exception {
        assertThat(Day22.iterate(Day22.parseInput(INPUT), 70), is(41));
        assertThat(Day22.iterate(Day22.parseInput(INPUT), 10000), is(5587));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day22.iterate2(Day22.parseInput(INPUT), 100), is(26));
        assertThat(Day22.iterate2(Day22.parseInput(INPUT), 10000000), is(2511944));
    }

}