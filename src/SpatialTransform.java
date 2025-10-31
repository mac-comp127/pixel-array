import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class SpatialTransform {
    private static double phase = 0;
    private static final double WAVE_SIZE = 42; 
    private static final double WAVE_LEN = 42; 

    public static void main(String[] args) {
        Image srcImage = new Image("campus-autumn.jpeg");
        int width = srcImage.getImageWidth();
        int height = srcImage.getImageHeight();
        int[] srcPixels = srcImage.toIntArray();
        int[] dstPixels = new int[srcPixels.length];
        CanvasWindow canvas = new CanvasWindow("Image", 900, 600);
        canvas.animate(() -> {
            for (int dstY = 0; dstY < height; dstY++) {
                for (int dstX = 0; dstX < width; dstX++) {
                    double theta = phase + Math.hypot(dstX - width / 2, dstY - height / 2) / 15.0
                        + phase / 20.0 * (Math.cos(dstX / 20.0) + Math.cos(dstY / 20.0));
                    int srcX = (int) (dstX + WAVE_SIZE * Math.cos(theta));
                    int srcY = (int) (dstY + WAVE_SIZE * Math.sin(theta));


                    if (srcX >= 0 && srcX < width && srcY >= 0 && srcY < height) {
                        dstPixels[dstX + dstY * width] = srcPixels[srcX + srcY * width];
                    }
                }
            }
            Image dstImage = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), dstPixels);
            canvas.removeAll();
            canvas.add(dstImage);
            phase += 0.05;
        });
    }
}
