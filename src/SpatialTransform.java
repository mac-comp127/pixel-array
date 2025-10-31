import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class SpatialTransform {
    private static double phase = 1;
    private static final double WAVE_SIZE = 12; 
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
                    int srcX = (int) (dstX + WAVE_SIZE * Math.cos(phase + dstY / WAVE_LEN));
                    int srcY = (int) (dstY + WAVE_SIZE * Math.cos(phase + dstX / WAVE_LEN));
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
