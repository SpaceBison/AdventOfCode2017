import java.util.Objects;

public class Day1 {
    public static void main(String... args) {
        String input = Common.getInputForDay(1);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String digitString) {
        return captcha(digitString, 1);
    }

    static int part2(String digitString) {
        return captcha(digitString, digitString.length() / 2);
    }

    private static int captcha(String digitString, int step) {
        int[] digits = digitString.chars().map(i -> Character.digit(i, 10)).toArray();

        int sum = 0;
        int length = digits.length;
        for (int i = 0; i < length; i++) {
            Integer digit = digits[i];
            if (Objects.equals(digit, digits[(i + step) % length])) {
                sum += digit;
            }
        }

        return sum;
    }
}
