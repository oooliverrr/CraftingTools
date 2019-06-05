package ai.neuralnets.images;

import java.io.Serializable;

import ai.neuralnets.math.IActivationFunction;

/**
 * 
 * @author Oliver
 *
 */
public class Filter implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Integer filterX, filterY, filterZ, filterVolume;
	private Hologram weightHologram, valueHologram;

	/**
	 * Two-dimensional Filter whose initial values = weights = 0.0
	 * 
	 * @param filterX
	 *            Width of the Filter
	 * @param filterY
	 *            Height of the Filter
	 */
	public Filter(Integer filterX, Integer filterY) {
		this(filterX, filterY, 1);
	}

	/**
	 * Two-dimensional Filter whose initial weights/values are determined by the
	 * activation function working on [-range, range]
	 * 
	 * @param filterX
	 *            Width of the Filter
	 * @param filterY
	 *            Height of the Filter
	 * @param activationFunction
	 *            Activation function used to compute initial values
	 * @param range
	 *            Range of the initial weights
	 */
	public Filter(Integer filterX, Integer filterY, IActivationFunction activationFunction, double range) {
		this(filterX, filterY, 1, activationFunction, range);
	}

	/**
	 * Three dimensional Filter whose initial values = weights = 0.0
	 * 
	 * @param filterX
	 *            Width of the Filter
	 * @param filterY
	 *            Height of the Filter
	 * @param filterZ
	 *            Depth of the Filter
	 */
	public Filter(Integer filterX, Integer filterY, Integer filterZ) {
		this.filterX = filterX;
		this.filterY = filterY;
		this.filterZ = filterZ;
		this.filterVolume = filterX * filterY * filterZ;
		weightHologram = new Hologram(filterX, filterY, filterZ);
		valueHologram = new Hologram(filterX, filterY, filterZ);
		fillZeros();
	}

	/**
	 * Three-dimensional Filter whose initial weights/values are determined by the
	 * activation function working on [-range, range]
	 * 
	 * @param filterX
	 *            Width of the Filter
	 * @param filterY
	 *            Height of the Filter
	 * @param filterZ
	 *            Depth of the Filter
	 * @param activationFunction
	 *            Activation function used to compute initial values
	 * @param range
	 *            Range of the initial weights
	 */
	public Filter(Integer filterX, Integer filterY, Integer filterZ, IActivationFunction activationFunction,
			double range) {
		this.filterX = filterX;
		this.filterY = filterY;
		this.filterZ = filterZ;
		this.filterVolume = filterX * filterY * filterZ;
		weightHologram = new Hologram(filterX, filterY, filterZ);
		valueHologram = new Hologram(filterX, filterY, filterZ);
		fillRandomFilter(activationFunction, range);
	}

	/**
	 * 
	 * @return Width of the Filter
	 */
	public Integer getFilterX() {
		return filterX;
	}

	/**
	 * 
	 * @return Height of the Filter
	 */
	public Integer getFilterY() {
		return filterY;
	}

	/**
	 * 
	 * @return Depth of the Filter
	 */
	public Integer getFilterZ() {
		return filterZ;
	}

	/**
	 * 
	 * @return Hologram representing the weights of the Filter (values are computed
	 *         using the weights and an activation function)
	 */
	public Hologram getWeightHologram() {
		return weightHologram;
	}

	/**
	 * 
	 * @return Hologram representing the values of the Filter (values are computed
	 *         using the weights and an activation function)
	 */
	public Hologram getValueHologram() {
		return valueHologram;
	}

	/**
	 * Fills the filter weights = values = 0.0
	 */
	private void fillZeros() {
		for (int i = 0; i < filterX; i++) {
			for (int j = 0; j < filterY; j++) {
				for (int k = 0; k < filterZ; k++) {
					Double randomWeight = 0.0;
					Double randomValue = 0.0;
					weightHologram.setMatrixIJK(i, j, k, randomWeight);
					valueHologram.setMatrixIJK(i, j, k, randomValue);
				}
			}
		}
	}

	/**
	 * The weights/values are determined by the activation function working on
	 * [-range, range]
	 * 
	 * @param activationFunction
	 *            Activation function used to compute values
	 * @param range
	 *            Range of the initial weights
	 */
	private void fillRandomFilter(IActivationFunction activationFunction, double range) {
		for (int i = 0; i < filterX; i++) {
			for (int j = 0; j < filterY; j++) {
				for (int k = 0; k < filterZ; k++) {
					Double randomWeight = -range + 2.0 * range * Math.random();
					Double randomValue = activationFunction.getValueAt(randomWeight);
					weightHologram.setMatrixIJK(i, j, k, randomWeight);
					valueHologram.setMatrixIJK(i, j, k, randomValue);
				}
			}
		}
	}

	/**
	 * Filters an Hologram
	 * 
	 * @param inputHologram
	 *            Input Hologram to be filtered
	 * @return New Hologram representing the filtered input Hologram
	 */
	public Hologram getFilteredHologram(Hologram inputHologram) {
		Integer hologramX = inputHologram.getNX();
		Integer hologramY = inputHologram.getNY();
		Integer hologramZ = inputHologram.getNZ();

		Integer newHologramX = hologramX - filterX + 1;
		Integer newHologramY = hologramY - filterY + 1;
		Integer newHologramZ = hologramZ - filterZ + 1;
		Hologram filteredHologram = new Hologram(newHologramX, newHologramY, newHologramZ);

		for (int deltaX = 0; deltaX < newHologramX; deltaX++) {
			for (int deltaY = 0; deltaY < newHologramY; deltaY++) {
				for (int deltaZ = 0; deltaZ < newHologramZ; deltaZ++) {
					Double totalSum = 0.0;
					for (int i = 0; i < filterX; i++) {
						for (int j = 0; j < filterY; j++) {
							for (int k = 0; k < filterZ; k++) {
								totalSum = totalSum + valueHologram.getValueAt(i, j, k)
										* inputHologram.getValueAt(deltaX + i, deltaY + j, deltaZ + k);
							}
						}
					}
					totalSum = totalSum / filterVolume;
					filteredHologram.setMatrixIJK(deltaX, deltaY, deltaZ, totalSum);
				}
			}
		}

		return filteredHologram;
	}
}
