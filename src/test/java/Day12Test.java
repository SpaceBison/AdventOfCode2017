import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day12Test {
    private static final String INPUT = "0 <-> 2\n"+
            "1 <-> 1\n"+
            "2 <-> 0, 3, 4\n"+
            "3 <-> 2, 4\n"+
            "4 <-> 2, 3, 6\n"+
            "5 <-> 6\n"+
            "6 <-> 4, 5";

    @Test
    public void part1() throws Exception {
        assertThat(Day12.part1(INPUT), is(6));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day12.part2(INPUT), is(2));
    }
}