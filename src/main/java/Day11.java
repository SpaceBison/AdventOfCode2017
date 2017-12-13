public class Day11 {
    public static void main(String... args) {
        String input = Common.getInputForDay(11);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        System.out.println(input);
        int x = 0;
        int y = 0;

        for (String direction : input.split(",")) {
            if (direction.length() == 1) {
                switch (direction) {
                    case "n":
                        y += 2;
                        break;
                    case "s":
                        y -= 2;
                        break;
                    case "w":
                        x -= 2;
                        break;
                    case "e":
                        x += 2;
                        break;
                }
            } else {
                switch (direction.charAt(0)) {
                    case 'n': y++; break;
                    case 's': y--; break;
                }

                switch (direction.charAt(1)) {
                    case 'w': x--; break;
                    case 'e': x++; break;
                }
            }
            System.out.println("" + x + " " + y);
        }


        int absy = Math.abs(y);
        int absx = Math.abs(x);
        return absx + (absy - absx)/2;
    }

    static int part2(String input) {

        System.out.println(input);
        int x = 0;
        int y = 0;
        int maxDistance = 0;

        for (String direction : input.split(",")) {
            if (direction.length() == 1) {
                switch (direction) {
                    case "n":
                        y += 2;
                        break;
                    case "s":
                        y -= 2;
                        break;
                    case "w":
                        x -= 2;
                        break;
                    case "e":
                        x += 2;
                        break;
                }
            } else {
                switch (direction.charAt(0)) {
                    case 'n': y++; break;
                    case 's': y--; break;
                }

                switch (direction.charAt(1)) {
                    case 'w': x--; break;
                    case 'e': x++; break;
                }
            }
            System.out.println("" + x + " " + y);

            int absy = Math.abs(y);
            int absx = Math.abs(x);
            maxDistance = Math.max(maxDistance, absx + (absy - absx)/2);
        }

        return maxDistance;
    }
}
