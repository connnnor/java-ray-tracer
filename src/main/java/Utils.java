public class Utils {
    private Utils() { }

    public static double randomDouble() {
        return Math.random();
    }

    public static double randomDouble(double min, double max) {
        return min + (max - min) * randomDouble();
    }

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;

    }

    public static double sin(double degrees) {
        return Math.sin(degreesToRadians(degrees));
    }

    public static double cos(double degrees) {
        return Math.cos(degreesToRadians(degrees));
    }
}