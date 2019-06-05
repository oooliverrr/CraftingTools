package ai.training.genetic.mutation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Oliver
 *
 * @param <GENE>
 */
public interface IMutator<GENE> extends Serializable {

	public static final long serialVersionUID = 1L;

	/**
	 * Mutates a single gene.
	 * 
	 * @param gene
	 *            Gene to mutate
	 * @return Mutated copy of the gene
	 */
	public GENE getMutatedGene(GENE gene);

	/**
	 * Mutates a list of genes.
	 * 
	 * @param genes
	 *            Genes to mutate
	 */
	public void mutateGenes(ArrayList<GENE> genes);
}
