public class Vec3 {
    private final double x;
    private final double y;
    private final double z;

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getZ() {
        return z;
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec3 randomVector() {
        return new Vec3(
                Utils.randomDouble(),
                Utils.randomDouble(),
                Utils.randomDouble());
    }

    public static Vec3 randomVector(double min, double max) {
        return new Vec3(
                Utils.randomDouble(min, max),
                Utils.randomDouble(min, max),
                Utils.randomDouble(min, max));
    }

    public static Vec3 randomVectorInUnitSphere() {
        while(true) {
            Vec3 p = randomVector(-1.0, 1.0);
            double lengthSquared = lengthSquared(p);
            if (lengthSquared(p) >= 1.0) { continue; }
            return unitVector(p);
        }

    }

    public static Vec3 add(Vec3 u, Vec3 v) {
        return new Vec3(
                u.getX() + v.getX(),
                u.getY() + v.getY(),
                u.getZ() + v.getZ());
    }

    public Vec3 add(Vec3 u) {
        return new Vec3(
                this.getX() + u.getX(),
                this.getY() + u.getY(),
                this.getZ() + u.getZ());
    }

    public static Vec3 add(Vec3 u, double c) {
        return new Vec3(
                u.getX() + c,
                u.getY() + c,
                u.getZ() + c);
    }

    public Vec3 add(double c) {
        return add(this, c);
    }

    public static Vec3 subtract(Vec3 u, Vec3 v) {
        return new Vec3(
                u.getX() - v.getX(),
                u.getY() - v.getY(),
                u.getZ() - v.getZ());
    }

    public Vec3 subtract(Vec3 u) {
        return new Vec3(
                this.getX() - u.getX(),
                this.getY() - u.getY(),
                this.getZ() - u.getZ());
    }

    public static Vec3 subtract(Vec3 u, double c) {
        return new Vec3(
                u.getX() - c,
                u.getY() - c,
                u.getZ() - c);
    }

    public static Vec3 negate(Vec3 u) {
        return multiply(u, -1.0);
    }

    public Vec3 negate() {
        return negate(this);
    }

    public static Vec3 unitVector(Vec3 u) {
        return divide(u, length(u));
    }

    public Vec3 unitVector() {
        return unitVector(this);
    }

    public static Vec3 multiply(Vec3 u, double c) {
        return new Vec3(c * u.getX(), c * u.getY(), c * u.getZ());
    }

    public Vec3 multiply(double c) {
        return multiply(this, c);
    }

    public static Vec3 multiply(Vec3 u, Vec3 v) {
        return new Vec3(
                u.getX() * v.getX(),
                u.getY() * v.getY(),
                u.getZ() * v.getZ());
    }

    public static Vec3 divide(Vec3 u, double c) {
        return new Vec3(u.getX() / c, u.getY() / c, u.getZ() / c);
    }

    public static double lengthSquared(Vec3 u) {
        return u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ();
    }

    public static double length(Vec3 u) {
        return Math.sqrt(lengthSquared(u));
    }

    public static Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(
                u.getY() * v.getZ() - u.getZ() * v.getY(),
                u.getZ() * v.getX() - u.getX() * v.getZ(),
                u.getX() * v.getY() - u.getY() * v.getX());
    }

    public static double dot(Vec3 u, Vec3 v) {
        return u.getX() * v.getX() +
                u.getY() * v.getY() +
                u.getZ() * v.getZ();
    }

    public static Vec3 sum(Vec3... vectors) {
        Vec3 summed = new Vec3(0, 0, 0);
        for (Vec3 v : vectors) {
            summed = summed.add(v);
        }
        return summed;
    }
}
