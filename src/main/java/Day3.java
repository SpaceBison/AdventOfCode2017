public class Day3 {
    public static void main(String... args) {
        int input = 325489;
    }

    public static int part1(int input) {
        double sqrt = Math.sqrt(input - 1);
        int n = (int) Math.floor(sqrt);
        int k = sqrt % 1 < 0.5 ? n : n + 1;
        int offset = input - 1 - n * k;
        System.out.format("input: %d = %d * %d + 1 + %d\n", input, n, k, offset);

        return 0;
    }
}
