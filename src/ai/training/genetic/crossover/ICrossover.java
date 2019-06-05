package ai.training.genetic.crossover;

import java.io.Serializable;

import ai.training.genetic.Beast;

/**
 * 
 * @author Oliver
 *
 * @param <GENE>
 */
public interface ICrossover<GENE> extends Serializable {

	public static final long serialVersionUID = 1L;

	/**
	 * Crosses two parents to get a child.
	 * 
	 * @param individualA
	 *            First parent
	 * @param individualB
	 *            Second parent
	 * @return Child
	 */
	public Beast<GENE> getCrossed(Beast<GENE> individualA, Beast<GENE> individualB);
}
