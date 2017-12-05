import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day5Test {
    @Test
    public void part1() throws Exception {
        String input = "0\n3\n0\n1\n-3";
        assertThat(Day5.part1(input), is(5));
    }

}