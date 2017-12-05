import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day4Test {
    @Test
    public void part2() throws Exception {
        String input = "abcde fghij\nabcde xyz ecdab\na ab abc abd abf abj\niiii oiii ooii oooi oooo\noiii ioii iioi iiio";
        assertThat(Day4.part2(input), is(3L));
    }

    @Test
    public void part1() throws Exception {
        String input = "aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa";
        assertThat(Day4.part1(input), is(2L));
    }

}