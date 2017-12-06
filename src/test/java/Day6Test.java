import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day6Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day6.part1("0\t2\t7\t0"), is(5));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day6.part2("0\t2\t7\t0"), is(4));
    }

}