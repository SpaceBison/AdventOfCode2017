import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day5Test {
    @Test
    public void part1() throws Exception {
        String input = "0\n3\n0\n1\n-3";
        assertThat(Day5.part1(input), is(5));
    }

    @Test
    public void part2() throws Exception {
        String input = "0\n3\n0\n1\n-3";
        assertThat(Day5.part2(input), is(10));
    }
}