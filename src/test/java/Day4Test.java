import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day4Test {
    @Test
    public void part1() throws Exception {
        String input = "aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa";
        assertThat(Day4.part1(input), is(2L));
    }

}