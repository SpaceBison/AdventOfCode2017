import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day18Test {
    private static final String INPUT1 = "set a 1\n"+
            "add a 2\n"+
            "mul a a\n"+
            "mod a 5\n"+
            "snd a\n"+
            "set a 0\n"+
            "rcv a\n"+
            "jgz a -1\n"+
            "set a 1\n"+
            "jgz a -2\n";

    private static final String INPUT2 = "snd 1\n"+
            "snd 2\n"+
            "snd p\n"+
            "rcv a\n"+
            "rcv b\n"+
            "rcv c\n"+
            "rcv d";

    @Test
    public void part1() throws Exception {
        assertThat(Day18.part1(INPUT1), is(4L));
    }

    @Test
    public void part2() throws Exception {
        assertThat(Day18.part2(INPUT2), is(3));
    }

}