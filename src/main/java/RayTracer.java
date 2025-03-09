import java.io.PrintStream;

public class RayTracer {
    public static void writePpmHeader(PrintStream out, int height, int width) {
        out.print("P3\n");
        out.print(width);
        out.print(' ');
        out.print(height);
        out.print("\n255\n");
    }

    public static void writeColor(PrintStream out, Vec3 pixelColor) {
        int red = (int) (255.999 * pixelColor.getX());
        int green = (int) (255.999 * pixelColor.getY());
        int blue = (int) (255.999 * pixelColor.getZ());

        out.print(red);
        out.print(' ');
        out.print(green);
        out.print(' ');
        out.print(blue);
        out.print('\n');
    }

    public static double hitSphere(Vec3 center, double radius, Ray ray) {
        Vec3 oc = center.subtract(ray.getOrigin());
        double a = Vec3.dot(ray.getDirection(), ray.getDirection());
        double b = -2.0 * Vec3.dot(ray.getDirection(), oc);
        double c = Vec3.dot(oc, oc) - radius * radius;
        double discriminant = b * b - 4.0 * a * c;
        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-b - Math.sqrt(discriminant)) / (2.0 * a);
        }
    }

    public static Vec3 rayColor(Ray ray) {
        Vec3 sphereOrigin = new Vec3(0, 0, -1.2);
        double sphereRadius = 0.5;
        double t = hitSphere(sphereOrigin, sphereRadius, ray);
        if (t > 0) {
            Vec3 normal = ray.pointAt(t).subtract(new Vec3(0, 0, -1)).unitVector();
            return normal.add(1.0).multiply(0.5);
        }
        Vec3 unitDirection = ray.getDirection().unitVector();
        double a = 0.5 * (unitDirection.getY() + 1.0);
        return Vec3.add(
                new Vec3(1.0, 1.0, 1.0).multiply(1.0 - a),
                new Vec3(0.5, 0.7, 1.0).multiply(a));
    }

    public static void main(String[] args) {
        double fieldOfViewDegrees = 45.0;
        double aspectRatio = 16.0 / 9.0;
        int imageWidth = 400;
        int imageHeight = (int) (((double) imageWidth) / aspectRatio);

        Vec3 lookFrom = new Vec3(-2, 2, 1);
        Vec3 lookAt = new Vec3(0, 0, -1);
        Camera camera = new Camera(fieldOfViewDegrees, aspectRatio, lookAt, lookFrom);

        writePpmHeader(System.out, imageHeight, imageWidth);
        for (int j = 0; j < imageHeight; j++) {
            System.err.print("\rScanlines remaining: ");
            System.err.print(imageHeight - j);
            System.err.flush();
            for (int i = 0; i < imageWidth; i++) {
                double u = ((double) i) / (imageWidth - 1);
                double v = ((double) j) / (imageHeight - 1);
                Ray r = camera.getRay(u, v);
                Vec3 pixelColor = rayColor(r);
                writeColor(System.out, pixelColor);
            }
        }
    }
}