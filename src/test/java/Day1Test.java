import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class Day1Test {
    @Test
    public void part1() throws Exception {
        Assert.assertThat(Day1.part1("1122"), CoreMatchers.is(3));
        Assert.assertThat(Day1.part1("1111"), CoreMatchers.is(4));
        Assert.assertThat(Day1.part1("1234"), CoreMatchers.is(0));
        Assert.assertThat(Day1.part1("91212129"), CoreMatchers.is(9));
    }

    @Test
    public void part2() throws Exception {
        Assert.assertThat(Day1.part2("1212"), CoreMatchers.is(6));
        Assert.assertThat(Day1.part2("1221"), CoreMatchers.is(0));
        Assert.assertThat(Day1.part2("123425"), CoreMatchers.is(4));
        Assert.assertThat(Day1.part2("123123"), CoreMatchers.is(12));
        Assert.assertThat(Day1.part2("12131415"), CoreMatchers.is(4));
    }
}