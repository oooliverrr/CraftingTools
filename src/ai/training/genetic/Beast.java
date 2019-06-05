package ai.training.genetic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Beast<GENE> implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<GENE> genes;
	private Comparator<GENE> geneComparator;

	public Beast(ArrayList<GENE> genes, Comparator<GENE> geneComparator) {
		this.genes = genes;
		this.geneComparator = geneComparator;
	}

	public boolean similarTo(Beast<GENE> otherIndividual) {
		ArrayList<GENE> genesOtherIndividual = otherIndividual.getGenes();
		for (int i = 0; i < genes.size(); i++) {
			GENE geneA = genes.get(i);
			GENE geneB = genesOtherIndividual.get(i);
			if (geneComparator.compare(geneA, geneB) != 0) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<GENE> getGenes() {
		return genes;
	}

	public GENE getGeneAt(int index) {
		return genes.get(index);
	}

	public Beast<GENE> setGeneAt(GENE newGene, int index) {
		genes.set(index, newGene);
		return this;
	}
}
