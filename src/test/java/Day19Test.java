import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day19Test {
    private static final String INPUT =
            "     |          \n" +
            "     |  +--+    \n" +
            "     A  |  C    \n" +
            " F---|----E|--+ \n" +
            "     |  |  |  D \n" +
            "     +B-+  +--+ \n" +
            "                ";

    @Test
    public void part1() throws Exception {
        assertThat(Day19.part1(INPUT), is("ABCDEF"));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day19.part2(INPUT), is(38));
    }

}