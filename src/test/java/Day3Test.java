import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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
}