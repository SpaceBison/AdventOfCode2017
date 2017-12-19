import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day17Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day17.part1(3), is(638));
    }

    @Test
    public void part2() throws Exception {
        Day17.part2(3);
    }

}