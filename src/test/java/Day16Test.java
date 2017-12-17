import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day16Test {
    private static final String INPUT = "s1,x3/4,pe/b";

    @Test
    public void part1() throws Exception {
        assertThat(Day16.part1(INPUT, 5), is("baedc"));
    }

    @Test
    public void part2() throws Exception {
    }

    @Test
    public void spin() {
        List<Character> list = asCharList("abcde");
        Day16.spin(list, 5);
        assertThat(list, is(asCharList("abcde")));
        Day16.spin(list, 1);
        assertThat(list, is(asCharList("eabcd")));
        Day16.spin(list, -1);
        assertThat(list, is(asCharList("abcde")));
    }

    private List<Character> asCharList(String string) {
        return string.chars().mapToObj(i -> (char)i).collect(Collectors.toList());
    }
}