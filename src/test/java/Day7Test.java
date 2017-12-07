import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class Day7Test {

    public static final String INPUT = "pbga (66)\n" +
            "xhth (57)\n" +
            "ebii (61)\n" +
            "havc (66)\n" +
            "ktlj (57)\n" +
            "fwft (72) -> ktlj, cntj, xhth\n" +
            "qoyq (66)\n" +
            "padx (45) -> pbga, havc, qoyq\n" +
            "tknk (41) -> ugml, padx, fwft\n" +
            "jptl (61)\n" +
            "ugml (68) -> gyxo, ebii, jptl\n" +
            "gyxo (61)\n" +
            "cntj (57)";

    @Test
    public void part1() throws Exception {
        Assert.assertThat(Day7.part1(INPUT), CoreMatchers.is("tknk"));
    }

    @Test
    public void part2() throws Exception {
        Assert.assertThat(Day7.part2(INPUT), CoreMatchers.is(60));
    }
}