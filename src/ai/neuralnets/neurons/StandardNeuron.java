package ai.neuralnets.neurons;

import ai.neuralnets.math.IActivationFunction;

/**
 * 
 * @author Oliver
 *
 */
public class StandardNeuron implements IGenericNeuron {

	private static final long serialVersionUID = 1L;

	protected double input, output, derivative, delta;

	protected final Integer ID;
	protected final IActivationFunction activationFunction;

	/**
	 * A generic kind of neuron (linear, sigmoid, etc)
	 * 
	 * @param ID
	 *            ID of the neuron
	 * @param activationFunction
	 *            The activation function
	 */
	public StandardNeuron(Integer ID, IActivationFunction activationFunction) {
		this.ID = ID;
		this.activationFunction = activationFunction;

		this.input = 0.0;
		this.output = 0.0;
		this.derivative = 0.0;
		this.delta = 0.0;
	}

	@Override
	public void addIncomeInformation(double weight, double value) {
		addIncomeInformation(weight, value, -1);
	}

	@Override
	public void addIncomeInformation(double weight, double value, int ID) {
		input = input + weight * value;
	}

	@Override
	public void activate() {
		output = activationFunction.getValueAt(input);
		derivative = activationFunction.getDerivativeAt(input);
	}

	@Override
	public void resetInput() {
		input = 0.0;
	}

	@Override
	public void resetDelta() {
		delta = 0.0;
	}

	@Override
	public void setDelta(double value) {
		delta = value;
	}

	@Override
	public void addDelta(double value) {
		delta = delta + value;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public double getInput() {
		return input;
	}

	@Override
	public double getOmega(double x, double y, int id) {
		return x;
	}

	@Override
	public double getGamma(double x, double y, int id) {
		return y;
	}

	@Override
	public double getOutput() {
		return output;
	}

	@Override
	public double getDerivative() {
		return derivative;
	}

	@Override
	public double getDelta() {
		return delta;
	}
}
