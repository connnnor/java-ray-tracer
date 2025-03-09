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

    public static void main(String[] args) {
        int imageWidth = 256;
        int imageHeight = 256;

        writePpmHeader(System.out, imageHeight, imageWidth);
        for (int j = 0; j < imageHeight; j++) {
            System.err.print("\rScanlines remaining: ");
            System.err.print(imageHeight - j);
            System.err.flush();
            for (int i = 0; i < imageWidth; i++) {
                Vec3 pixelColor = new Vec3(
                        ((double) i) / (imageWidth - 1),
                        ((double) j) / (imageHeight - 1),
                        0);
                writeColor(System.out, pixelColor);
            }
        }
    }
}