import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day10Test {
    @Test
    public void reverseRange() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Day10.wrappedReverseRange(list, 0, 3);
        assertThat(list, is(Arrays.asList(3, 2, 1, 4, 5)));

        Day10.wrappedReverseRange(list, 3, 7);
        assertThat(list, is(Arrays.asList(5, 4, 1, 2, 3)));
    }

    @Test
    public void part1() throws Exception {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4);
        int current = 0;
        int skip = 0;
        for (Integer length : Arrays.asList(3, 4, 1, 5)) {
            Day10.wrappedReverseRange(list, current, current + length);
            current += length + skip++;
        }
        assertThat(list, is(Arrays.asList(3, 4, 2, 1, 0)));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day10.part2("1,2,3"), is("3efbe78a8d82f29979031a4aa0b16a9d"));
        assertThat(Day10.part2(""), is("a2582a3a0e66e6e86e3812dcb672a272"));
        assertThat(Day10.part2("AoC 2017"), is("33efeb34ea91902bb2f59c9920caa6cd"));
        assertThat(Day10.part2("1,2,4"), is("63960835bcdc130f0b66d7ff4f6a5a8e"));
    }

}