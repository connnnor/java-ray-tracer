public class Camera {
    private final double aspectRatio;
    private final Vec3 lookAt;
    private final Vec3 lookFrom;
    private final Vec3 viewUp = new Vec3(0.0, 1.0, 0.0);

    private final Vec3 horizontal;
    private final Vec3 vertical;
    private final Vec3 lowLeft;

    public Camera(double fieldOfViewDegrees, double aspectRatio, Vec3 lookAt, Vec3 lookFrom) {
        this.aspectRatio = aspectRatio;
        this.lookAt = lookAt;
        this.lookFrom = lookFrom;
        // math stuff
        double theta = Utils.degreesToRadians(fieldOfViewDegrees);
        double h = Math.tan(theta / 2.0);
        double viewportHeight  = 2.0 * h;
        double viewportWidth  = aspectRatio * viewportHeight;
        // camera view orientation
        Vec3 w = lookFrom.subtract(lookAt).unitVector();
        Vec3 u = Vec3.cross(viewUp, w).unitVector();
        Vec3 v = Vec3.cross(w, u);
        //
        horizontal = u.multiply(viewportWidth);
        vertical = v.multiply(viewportHeight);
        lowLeft = Vec3.sum(
                lookFrom,
                horizontal.multiply(-0.5),
                vertical.multiply(-0.5),
                w.negate());
    }

    public Ray getRay(double u, double v) {
        Vec3 direction = Vec3.sum(
                lowLeft,
                horizontal.multiply(u),
                vertical.multiply(v),
                lookFrom.negate());
        return new Ray(lookFrom, direction);
    }
}
