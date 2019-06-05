package ai.neuralnets.images;

import java.io.Serializable;

/**
 * 
 * @author Oliver
 *
 */
public class Pooler implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Integer poolX, poolY, poolZ;

	/**
	 * Two-dimensional Pooler
	 * 
	 * @param poolX
	 *            Width of the Pooler
	 * @param poolY
	 *            Height of the Pooler
	 */
	public Pooler(Integer poolX, Integer poolY) {
		this(poolX, poolY, 1);
	}

	/**
	 * Three-dimensional Pooler
	 * 
	 * @param poolX
	 *            Width of the Pooler
	 * @param poolY
	 *            Height of the Pooler
	 * @param poolZ
	 *            Depth of the Pooler
	 */
	public Pooler(Integer poolX, Integer poolY, Integer poolZ) {
		this.poolX = poolX;
		this.poolY = poolY;
		this.poolZ = poolZ;
	}

	/**
	 * 
	 * @return Width of the Pooler
	 */
	public Integer getPoolX() {
		return poolX;
	}

	/**
	 * 
	 * @return Height of the Pooler
	 */
	public Integer getPoolY() {
		return poolY;
	}

	/**
	 * 
	 * @return Depth of the Pooler
	 */
	public Integer getPoolZ() {
		return poolZ;
	}

	/**
	 * 
	 * @param inputHologram
	 *            Input Hologram to be pooled
	 * @return New Hologram representing the pooled input Hologram
	 */
	public Hologram getPooledHologram(Hologram inputHologram) {
		Integer hologramX = inputHologram.getNX();
		Integer hologramY = inputHologram.getNY();
		Integer hologramZ = inputHologram.getNZ();

		Integer newHologramX = hologramX / poolX;
		if (hologramX % poolX > 0) {
			newHologramX = newHologramX + 1;
		}
		Integer newHologramY = hologramY / poolY;
		if (hologramY % poolY > 0) {
			newHologramY = newHologramY + 1;
		}
		Integer newHologramZ = hologramZ / poolZ;
		if (hologramZ % poolZ > 0) {
			newHologramZ = newHologramZ + 1;
		}
		Hologram pooledHologram = new Hologram(newHologramX, newHologramY, newHologramZ);

		for (int deltaX = 0; deltaX < newHologramX; deltaX++) {
			for (int deltaY = 0; deltaY < newHologramY; deltaY++) {
				for (int deltaZ = 0; deltaZ < newHologramZ; deltaZ++) {
					Double maxValue = null;
					for (int i = 0; i < poolX; i++) {
						for (int j = 0; j < poolY; j++) {
							for (int k = 0; k < poolZ; k++) {
								Double challengerValue = inputHologram.getValueAt(deltaX * newHologramX + i,
										deltaY * newHologramY + j, deltaZ * newHologramZ + k);
								if (maxValue == null) {
									maxValue = challengerValue;
								} else {
									if (challengerValue > maxValue) {
										maxValue = challengerValue;
									}
								}
							}
						}
					}
					pooledHologram.setMatrixIJK(deltaX, deltaY, deltaZ, maxValue);
				}
			}
		}

		return pooledHologram;
	}
}
