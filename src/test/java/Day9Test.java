import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day9Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day9.part1("{}"), is(1));
        assertThat(Day9.part1("{{{}}}"), is(6));
        assertThat(Day9.part1("{{},{}}"), is(5));
        assertThat(Day9.part1("{{{},{},{{}}}}"), is(16));
        assertThat(Day9.part1("{<a>,<a>,<a>,<a>}"), is(1));
        assertThat(Day9.part1("{{<ab>},{<ab>},{<ab>},{<ab>}}"), is(9));
        assertThat(Day9.part1("{{<!!>},{<!!>},{<!!>},{<!!>}}"), is(9));
        assertThat(Day9.part1("{{<a!>},{<a!>},{<a!>},{<ab>}}"), is(3));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day9.part2("<>"), is(0));
        assertThat(Day9.part2("<random characters>"), is(17));
        assertThat(Day9.part2("<<<<>"), is(3));
        assertThat(Day9.part2("<{!>}>"), is(2));
        assertThat(Day9.part2("<!!>"), is(0));
        assertThat(Day9.part2("<!!!>>"), is(0));
        assertThat(Day9.part2("<{o\"i!a,<{i<a>"), is(10));
    }

}