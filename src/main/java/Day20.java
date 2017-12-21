import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day20 {
    public static void main(String... args) {
        String input = Common.getInputForDay(20);
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    static int part1(String input) {
        List<Particle> particles = getParticles(input);
        particles.sort(Comparator.comparingInt(v -> v.getAcceleration().distanceFromBeginning()));

        return particles.get(0).getId();
    }

    static int part2(String input) {
        List<Particle> particles = getParticles(input);
        particles.sort(Comparator.comparingInt(v -> v.getAcceleration().distanceFromBeginning()));

        while (!particles.stream().allMatch(Particle::hasStableDirection) ||
                IntStream.range(0, particles.size() - 1)
                .allMatch(i -> particles.get(i).position.distanceFromBeginning() < particles.get(i + 1).position.distanceFromBeginning())) {
            Set<Particle> collisions = new HashSet<>();
            for (int i = 0; i < particles.size() - 1; ++i) {
                Particle particle = particles.get(i);
                for (int j = i + 1; j < particles.size(); ++j) {
                    Particle otherParticle = particles.get(j);
                    if (particle.position.equals(otherParticle.position)) {
                        collisions.add(particle);
                        collisions.add(otherParticle);
                    }
                }
            }

            particles.removeAll(collisions);
            particles.forEach(Particle::step);
        }

        return particles.size();
    }

    private static List<Particle> getParticles(String input) {
        List<Map<String, Vector>> vectorTuples = Arrays.stream(input.split("\n"))
                .map(l -> Arrays.stream(l.split(", "))
                        .map(v -> Arrays.stream(v.split("[=<,> ]"))
                                .filter(s -> !s.isEmpty())
                                .toArray(String[]::new))
                        .collect(Collectors.toMap(a -> a[0], a -> new Vector(
                                Integer.parseInt(a[1]),
                                Integer.parseInt(a[2]),
                                Integer.parseInt(a[3])))))
                .collect(Collectors.toList());

        return IntStream.range(0, vectorTuples.size())
                .mapToObj(i -> new Particle(
                        i,
                        vectorTuples.get(i).get("p"),
                        vectorTuples.get(i).get("v"),
                        vectorTuples.get(i).get("a")))
                .collect(Collectors.toList());
    }

    private static class Particle {
        private final int id;
        private Vector position;
        private Vector velocity;
        private Vector acceleration;

        public Particle(int id, Vector position, Vector velocity, Vector acceleration) {
            this.id = id;
            this.position = position;
            this.velocity = velocity;
            this.acceleration = acceleration;
        }

        public void step() {
            velocity = velocity.add(acceleration);
            position = position.add(velocity);
        }

        public Vector getPosition() {
            return position;
        }

        public Vector getVelocity() {
            return velocity;
        }

        public Vector getAcceleration() {
            return acceleration;
        }

        public boolean hasStableDirection() {
            return (position.x >= 0 && velocity.x >= 0 && acceleration.x >= 0 || position.x <= 0 && velocity.x <= 0 && acceleration.x <= 0) &&
                    (position.y >= 0 && velocity.y >= 0 && acceleration.y >= 0 || position.y <= 0 && velocity.y <= 0 && acceleration.y <= 0) &&
                    (position.z >= 0 && velocity.z >= 0 && acceleration.z >= 0 || position.z <= 0 && velocity.z <= 0 && acceleration.z <= 0);
        }

        @Override
        public String toString() {
            return "Particle{" + id +
                    ", p=" + position +
                    ", v=" + velocity +
                    ", a=" + acceleration +
                    ", d=" + getPosition().distanceFromBeginning() + '}';
        }

        public int getId() {
            return id;
        }
    }

    private static class Vector {
        private final int x, y, z;

        private Vector(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private int distanceFromBeginning() {
            return Math.abs(x) + Math.abs(y) + Math.abs(z);
        }

        private Vector add(Vector other) {
            return new Vector(x + other.x, y + other.y, z + other.z);
        }

        @Override
        public String toString() {
            return String.format("<%3d,%3d,%3d>", x, y, z);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vector vector = (Vector) o;

            if (x != vector.x) return false;
            if (y != vector.y) return false;
            return z == vector.z;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }
    }
}
