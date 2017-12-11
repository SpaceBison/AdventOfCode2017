import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day11Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day11.part1("ne,ne,ne"), is(3));
        assertThat(Day11.part1("ne,ne,sw,sw"), is(0));
        assertThat(Day11.part1("ne,ne,s,s"), is(2));
        assertThat(Day11.part1("se,sw,se,sw,sw"), is(3));
    }

    @Test
    public void part2() throws Exception {

    }

}