package files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Oliver
 *
 */
public class ImageManager {

	/**
	 * 
	 * @param filePath
	 *            Path of image
	 * @return Image
	 */
	public static BufferedImage getImageFromFile(String filePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			System.out.println("{ImageManager} - Error reading image [" + filePath + "]: " + e.getMessage());
		}
		return img;
	}
}
