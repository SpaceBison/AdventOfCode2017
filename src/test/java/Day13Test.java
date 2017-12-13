import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day13Test {
    private static final String INPUT = "0: 3\n" +
            "1: 2\n" +
            "4: 4\n" +
            "6: 4";

    @Test
    public void part1() throws Exception {
        assertThat(Day13.part1(INPUT), is(24));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day13.part2(INPUT), is(10));
    }

}