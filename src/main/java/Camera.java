public class Camera {
    private final double fieldOfViewDegrees;
    private final double aspectRatio;
    private final Vec3 lookAt;
    private final Vec3 lookFrom;
    private final Vec3 viewUp;

    public Camera(double fieldOfViewDegrees, double aspectRatio, Vec3 lookAt, Vec3 lookFrom, Vec3 viewUp) {
        this.fieldOfViewDegrees = fieldOfViewDegrees;
        this.aspectRatio = aspectRatio;
        this.lookAt = lookAt;
        this.lookFrom = lookFrom;
        this.viewUp = viewUp;
    }
}
