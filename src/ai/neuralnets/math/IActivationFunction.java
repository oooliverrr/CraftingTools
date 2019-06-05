package ai.neuralnets.math;

import java.io.Serializable;

/**
 * 
 * @author Oliver
 *
 */
public interface IActivationFunction extends Serializable {

	public static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param x
	 *            x
	 * @return f(x)
	 */
	public double getValueAt(double x);

	/**
	 * 
	 * @param x
	 *            x
	 * @return f'(x)
	 */
	public double getDerivativeAt(double x);
}
