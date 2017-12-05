import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day3Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day3.part1(1), is(0));
        assertThat(Day3.part1(2), is(1));
        assertThat(Day3.part1(3), is(2));
        assertThat(Day3.part1(4), is(1));
        assertThat(Day3.part1(5), is(2));
        assertThat(Day3.part1(6), is(1));
        assertThat(Day3.part1(7), is(2));
        assertThat(Day3.part1(8), is(1));
        assertThat(Day3.part1(9), is(2));
        assertThat(Day3.part1(12), is(3));
        assertThat(Day3.part1(23), is(2));
        assertThat(Day3.part1(1024), is(31));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day3.part2(1), is(2));
        assertThat(Day3.part2(2), is(4));
        assertThat(Day3.part2(3), is(4));
        assertThat(Day3.part2(4), is(5));
        assertThat(Day3.part2(5), is(10));
        assertThat(Day3.part2(7), is(10));
        assertThat(Day3.part2(8), is(10));
        assertThat(Day3.part2(9), is(10));
        assertThat(Day3.part2(10), is(11));
        assertThat(Day3.part2(11), is(23));
        assertThat(Day3.part2(12), is(23));
        assertThat(Day3.part2(13), is(23));
        assertThat(Day3.part2(23), is(25));
        assertThat(Day3.part2(800), is(806));
    }

    /*      147  142  133  122   59
            304    5    4    2   57
            330   10    1    1   54
            351   11   23   25   26
            362  747  806--->   ...*/
}