import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day15Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day15.part1(65, 8921), is(588L));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day15.part2(65, 8921), is(309L));
    }

}