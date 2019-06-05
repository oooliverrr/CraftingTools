package ai.neuralnets.math;

/**
 * 
 * @author Oliver
 *
 */
public class SigmoidActivationFunction implements IActivationFunction {

	private static final long serialVersionUID = 1L;

	private final double max, lambda;
	private final double expLambdaThreshold, minExpLambdaThreshold, derivativeAux;

	/**
	 * <pre>
	 * Sigmoid activation function
	 * f(x) = min + (max-min)/(1+exp(-lambda*(x-T)))
	 * f'(x) =  lambda*(max-min)*exp(-lambda*(x-T))/(1+exp(-lambda*(x-T)))^2
	 * </pre>
	 * 
	 * @param min
	 * @param max
	 * @param lambda
	 * @param threshold
	 */
	public SigmoidActivationFunction(double min, double max, double lambda, double threshold) {
		this.max = max;
		this.lambda = lambda;

		this.expLambdaThreshold = Math.exp(lambda * threshold);
		this.minExpLambdaThreshold = min * Math.exp(lambda * threshold);
		this.derivativeAux = lambda * (max - min) * Math.exp(lambda * threshold);
	}

	@Override
	public double getValueAt(double x) {
		double exp = Math.exp(-lambda * x);
		return (max + minExpLambdaThreshold * exp) / (1 + expLambdaThreshold * exp);
	}

	@Override
	public double getDerivativeAt(double x) {
		double exp = Math.exp(-lambda * x);
		double denominator = 1 + expLambdaThreshold * exp;
		denominator = denominator * denominator;
		return derivativeAux * exp / denominator;
	}
}
