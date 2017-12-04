public class Day3 {
    public static void main(String... args) {
        int input = 325489;
        System.out.println(part1(input));
    }

    public static int part1(int input) {
        double sqrt = Math.sqrt(input - 1);
        int n = (int) Math.floor(sqrt);
        int k = sqrt % 1 < 0.5 ? n : n + 1;

        int x = n % 2 == 0 ? n / (-2) : (n + 1) / 2;
        int y = k % 2 == 0 ? k / 2 : k / (-2);

        int offset = input - 1 - n * k;

        System.out.format("input: %d = %d * %d + 1 + %d corner: [%2d:%-2d]", input, n, k, offset, x, y);

        if (n != k) { // horizontal
            x = y > 0 ? x - offset : x + offset;
        } else {
            y = x >= 0 ? y + offset : y - offset;
        }

        System.out.format(" coord: [%2d:%-2d]\n", x, y);

        return Math.abs(x) + Math.abs(y);
    }
}
