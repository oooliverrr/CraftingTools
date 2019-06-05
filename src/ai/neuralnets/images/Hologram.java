package ai.neuralnets.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Oliver
 *
 */
public class Hologram implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nX, nY, nZ;
	private double[][][] matrix;

	/**
	 * Two-dimensional hologram, ie 2D image
	 * 
	 * @param nX
	 *            Width of the Hologram
	 * @param nY
	 *            Height of the Hologram
	 */
	public Hologram(Integer nX, Integer nY) {
		this(nX, nY, 1);
	}

	/**
	 * Three-dimensional Hologram
	 * 
	 * @param nX
	 *            Width of the Hologram
	 * @param nY
	 *            Height of the Hologram
	 * @param nZ
	 *            Depth of the Hologram
	 */
	public Hologram(Integer nX, Integer nY, Integer nZ) {
		this.nX = nX;
		this.nY = nY;
		this.nZ = nZ;
		matrix = new double[nX][nY][nZ];
	}

	/**
	 * Hologram representation of a collection of 2D images, stacked one after the
	 * other
	 * 
	 * @param images
	 *            ArrayList of images
	 */
	public Hologram(ArrayList<BufferedImage> images) {
		nZ = images.size();
		if (nZ > 0) {
			for (int k = 0; k < nZ; k++) {
				BufferedImage image = images.get(k);
				if (nX == null) {
					nX = image.getWidth();
					nY = image.getHeight();
					matrix = new double[nX][nY][nZ];
				}
				for (int i = 0; i < nX; i++) {
					for (int j = 0; j < nY; j++) {
						Color RGB = new Color(image.getRGB(i, j));
						int R = RGB.getRed();
						int G = RGB.getGreen();
						int B = RGB.getBlue();
						double averageRGB = (R + G + B) / 3.0;
						double normalizedRGB = -1.0 + 2.0 * averageRGB / 255.0;
						matrix[i][j][k] = normalizedRGB;
					}
				}
			}
		}
	}

	/**
	 * Changes a particular value of the Hologram representation matrix
	 * 
	 * @param i
	 *            x-pixel
	 * @param j
	 *            y-pixel
	 * @param k
	 *            z-pixel
	 * @param value
	 *            Value to be set on the representation matrix
	 * @return This
	 */
	public Hologram setMatrixIJK(Integer i, Integer j, Integer k, Double value) {
		matrix[i][j][k] = value;
		return this;
	}

	/**
	 * 
	 * @return Width of the Hologram
	 */
	public Integer getNX() {
		return nX;
	}

	/**
	 * 
	 * @return Height of the Hologram
	 */
	public Integer getNY() {
		return nY;
	}

	/**
	 * 
	 * @return Depth of the Hologram
	 */
	public Integer getNZ() {
		return nZ;
	}

	/**
	 * 
	 * @return Representation matrix of the Hologram
	 */
	public double[][][] getMatrix() {
		return matrix;
	}

	/**
	 * 
	 * @param i
	 *            x-pixel
	 * @param j
	 *            y-pixel
	 * @param k
	 *            z-pixel
	 * @return The value at (x, y, z) of the representation matrix
	 */
	public double getValueAt(int i, int j, int k) {
		return matrix[i][j][k];
	}
}
