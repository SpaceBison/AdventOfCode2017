import java.util.Set;
import java.util.TreeSet;

public class Day25 {
    public static void main(String... args) {
        //String input = Common.getInputForDay(0);
        System.out.println(part1());
        //System.out.println(part2(input));
    }

    static int part1() {
        TuringMachine machine = new TuringMachine();
        State state = State.A;

        for (int i = 0; i < 12172063; ++i) {
            switch (state) {
                case A:
                    if (!machine.get()) {
                        machine.set(true);
                        machine.move(1);
                        state = State.B;
                    } else {
                        machine.set(false);
                        machine.move(-1);
                        state = State.C;
                    }
                    break;
                case B:
                    if (!machine.get()) {
                        machine.set(true);
                        machine.move(-1);
                        state = State.A;
                    } else {
                        machine.set(true);
                        machine.move(-1);
                        state = State.D;
                    }
                    break;
                case C:
                    if (!machine.get()) {
                        machine.set(true);
                        machine.move(1);
                        state = State.D;
                    } else {
                        machine.set(false);
                        machine.move(1);
                        state = State.C;
                    }
                    break;
                case D:
                    if (!machine.get()) {
                        machine.set(false);
                        machine.move(-1);
                        state = State.B;
                    } else {
                        machine.set(false);
                        machine.move(1);
                        state = State.E;
                    }
                    break;
                case E:
                    if (!machine.get()) {
                        machine.set(true);
                        machine.move(1);
                        state = State.C;
                    } else {
                        machine.set(true);
                        machine.move(-1);
                        state = State.F;
                    }
                    break;
                case F:
                    if (!machine.get()) {
                        machine.set(true);
                        machine.move(-1);
                        state = State.E;
                    } else {
                        machine.set(true);
                        machine.move(1);
                        state = State.A;
                    }
                    break;
            }
        }

        return machine.getCheckSum();
    }

    static int part2(String input) {
        return 0;
    }

    private static class TuringMachine {
        private final Set<Integer> mSetBits = new TreeSet<>();
        private int mPosition = 0;

        public void move(int offset) {
            mPosition += offset;
        }

        public boolean get() {
            return mSetBits.contains(mPosition);
        }

        public void set(boolean value) {
            if (value) {
                mSetBits.add(mPosition);
            } else {
                mSetBits.remove(mPosition);
            }
        }

        public int getCheckSum() {
            return mSetBits.size();
        }
    }

    private enum State {
        A, B, C, D, E, F
    }
}
