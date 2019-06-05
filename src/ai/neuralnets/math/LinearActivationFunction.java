package ai.neuralnets.math;

/**
 * 
 * @author Oliver
 *
 */
public class LinearActivationFunction implements IActivationFunction {

	private static final long serialVersionUID = 1L;

	private final double m, n;

	/**
	 * <pre>
	 * Linear activation function
	 * f(x) = m*x + n
	 * f'(x) = m
	 * </pre>
	 * 
	 * @param m
	 *            slope
	 * @param n
	 *            y-intercept
	 */
	public LinearActivationFunction(double m, double n) {
		this.m = m;
		this.n = n;
	}

	@Override
	public double getValueAt(double x) {
		return m * x + n;
	}

	@Override
	public double getDerivativeAt(double x) {
		return m;
	}
}
