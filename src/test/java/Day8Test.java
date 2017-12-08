import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day8Test {
    private static final String INPUT =
            "b inc 5 if a > 1\n" +
                    "a inc 1 if b < 5\n" +
                    "c dec -10 if a >= 1\n" +
                    "c inc -20 if c == 10";

    @Test
    public void part1() throws Exception {
        Assert.assertThat(Day8.part1(INPUT), is(1));
    }

    @Test
    public void part2() throws Exception {
        Assert.assertThat(Day8.part2(INPUT), is(10));
    }

}