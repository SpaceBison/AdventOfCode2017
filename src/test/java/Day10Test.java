import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day10Test {
    @Test
    public void reverseRange() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Day10.reverseRange(list, 0, 3);
        assertThat(list, is(Arrays.asList(3, 2, 1, 4, 5)));

        Day10.reverseRange(list, 3, 7);
        assertThat(list, is(Arrays.asList(5, 4, 1, 2, 3)));
    }

    @Test
    public void part1() throws Exception {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4);
        int value = Day10.part1Impl(list, Arrays.asList(3, 4, 1, 5));
        assertThat(value, is(12));
    }

    @Test
    public void part2() throws Exception {
    }

}