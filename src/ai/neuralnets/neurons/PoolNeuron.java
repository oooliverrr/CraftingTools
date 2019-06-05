package ai.neuralnets.neurons;

import ai.neuralnets.math.LinearActivationFunction;

/**
 * 
 * @author Oliver
 *
 */
public class PoolNeuron extends StandardNeuron {

	private static final long serialVersionUID = 1L;

	private int largestContributorID;

	/**
	 * Special kind of neuron: pool neuron
	 * 
	 * @param ID
	 *            ID of the neuron
	 */
	public PoolNeuron(Integer ID) {
		super(ID, new LinearActivationFunction(1.0, 0.0));

		this.input = -Double.MAX_VALUE;
		this.largestContributorID = -1;
	}

	@Override
	public void addIncomeInformation(double weight, double value, int ID) {
		if (value > input) {
			input = value;
			largestContributorID = ID;
		}
	}

	@Override
	public void resetInput() {
		input = -Double.MAX_VALUE;
	}

	@Override
	public double getOmega(double x, double y, int id) {
		if (largestContributorID == id) {
			return x;
		} else {
			return 0;
		}
	}

	@Override
	public double getGamma(double x, double y, int id) {
		if (largestContributorID == id) {
			return y;
		} else {
			return 0;
		}
	}
}
