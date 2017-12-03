import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day2Test {
    @Test
    public void part1() throws Exception {
        String input =
                "5 1 9 5\n" +
                        "7 5 3\n" +
                        "2 4 6 8";
        assertThat(Day2.part1(input), is(18));
    }

    @Test
    public void part2() {
        String input =
                "5 9 2 8\n" +
                        "9 4 7 3\n" +
                        "3 8 6 5";
        assertThat(Day2.part2(input), is(9));
    }
}