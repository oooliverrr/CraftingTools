package ai.training.genetic.fitness;

import java.io.Serializable;
import java.util.ArrayList;

import ai.training.genetic.Beast;

/**
 * 
 * @author Oliver
 *
 * @param <GENE>
 */
public interface IFitnessEvaluator<GENE> extends Serializable {

	public static final long serialVersionUID = 1L;

	public void evaluateFitness(ArrayList<Beast<GENE>> population);

	public double getFitness(Beast<GENE> individual);
}
