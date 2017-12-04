import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day3Test {
    @Test
    public void part1() throws Exception {
        assertThat(Day3.part1(1), is(0));
        assertThat(Day3.part1(12), is(3));
        assertThat(Day3.part1(23), is(2));
        assertThat(Day3.part1(1024), is(31));
    }
}