import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Day20Test {
    private static final String INPUT1 = "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>\n" +
            "p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>";

    private static final String INPUT2 = "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>\n" +
            "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>\n" +
            "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>\n" +
            "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>";

    @Test
    public void part1() throws Exception {
        assertThat(Day20.part1(INPUT1), is(0));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day20.part2(INPUT2), is(1));
    }

}