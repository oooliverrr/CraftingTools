package ai.neuralnets.neurons;

import java.io.Serializable;

/**
 * 
 * @author Oliver
 *
 */
public interface IGenericNeuron extends Serializable {

	public static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param weight
	 *            Income Link weight
	 * @param value
	 *            Income Neuron value
	 */
	public void addIncomeInformation(double weight, double value);

	/**
	 * 
	 * @param weight
	 *            Income Link weight
	 * @param value
	 *            Income Neuron value
	 * @param ID
	 *            ID of the income neuron
	 */
	public void addIncomeInformation(double weight, double value, int ID);

	/**
	 * Using the AsctivationFunction, computes the output given the current input
	 */
	public void activate();

	/**
	 * Resets the current input
	 */
	public void resetInput();

	/**
	 * Resets the delta value (error)
	 */
	public void resetDelta();

	/**
	 * Sets the delta value (error)
	 * 
	 * @param value
	 *            New delta value
	 */
	public void setDelta(double value);

	/**
	 * Adds some value to the delta value (error)
	 * 
	 * @param value
	 *            Value to be added to the delta value (error)
	 */
	public void addDelta(double value);

	/**
	 * 
	 * @return ID of the neuron
	 */
	public int getID();

	/**
	 * 
	 * @return The current input value
	 */
	public double getInput();

	/**
	 * 
	 * @param x
	 * @param y
	 * @param id
	 * @return Omega value (see math notes)
	 */
	public double getOmega(double x, double y, int id);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param id
	 * @return Gamma value (see math notes)
	 */
	public double getGamma(double x, double y, int id);

	/**
	 * 
	 * @return The current output value
	 */
	public double getOutput();

	/**
	 * 
	 * @return The current derivative value
	 */
	public double getDerivative();

	/**
	 * 
	 * @return The current delta value (error)
	 */
	public double getDelta();
}
