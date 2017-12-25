import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day24Test {
    private static final String INPUT = "0/2\n" +
            "2/2\n" +
            "2/3\n" +
            "3/4\n" +
            "3/5\n" +
            "0/1\n" +
            "10/1\n" +
            "9/10";

    @Test
    public void part1() throws Exception {
        assertThat(Day24.part1(INPUT), is(31));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day24.part2(INPUT), is(19));
    }

}