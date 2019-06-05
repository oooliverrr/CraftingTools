package ai.training.genetic.mutation;

import java.util.ArrayList;

import util.storage.Pair;

/**
 * 
 * @author Oliver
 *
 */
public class Mutator2DPoints implements IMutator<Pair<Double, Double>> {

	public static final long serialVersionUID = 1L;

	private final double mutationRate;

	/**
	 * 
	 * @param mutationRate
	 *            Rate at which mutations occur.
	 */
	public Mutator2DPoints(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	/**
	 * A single Pair of points cannot be mutated. Just return the gene.
	 */
	public Pair<Double, Double> getMutatedGene(Pair<Double, Double> gene) {
		return gene;
	}

	/**
	 * Mutate a list of genes.
	 */
	public void mutateGenes(ArrayList<Pair<Double, Double>> genes) {
		int numberOfGenes = genes.size();
		for (int i = 0; i < numberOfGenes; i++) {
			/*
			 * Only mutate at some predefine mutation rate.
			 */
			if (100.0 * Math.random() <= mutationRate) {
				double random = Math.random();
				if (random <= 0.3) {
					displaceGenes(genes, i);
				} else if (random <= 0.9) {
					swap2Genes(genes, i);
				} else if (random <= 0.96) {
					swap3Genes(genes, i);
				} else {
					swap4Genes(genes, i);
				}
			}
		}
	}

	private void displaceGenes(ArrayList<Pair<Double, Double>> genes, int i) {
		int numberOfGenes = genes.size();
		int randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomJ == i) {
			randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		}

		int randomI = i;
		if (randomJ < randomI) {
			int aux = randomJ;
			randomJ = randomI;
			randomI = aux;
		}

		Pair<Double, Double> geneI = genes.get(randomI);

		for (int pos = randomI; pos < randomJ; pos++) {
			genes.set(pos, genes.get(pos + 1));
		}
		genes.set(randomJ, geneI);
	}

	private void swap2Genes(ArrayList<Pair<Double, Double>> genes, int i) {
		int numberOfGenes = genes.size();
		int randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomJ == i) {
			randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		}
		Pair<Double, Double> geneI = genes.get(i);
		Pair<Double, Double> geneJ = genes.get(randomJ);
		genes.set(i, geneJ);
		genes.set(randomJ, geneI);
	}

	private void swap3Genes(ArrayList<Pair<Double, Double>> genes, int i) {
		int numberOfGenes = genes.size();
		int randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomJ == i) {
			randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		}
		int randomK = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomK == i || randomK == randomJ) {
			randomK = (int) (Math.floor(numberOfGenes * Math.random()));
		}
		Pair<Double, Double> geneI = genes.get(i);
		Pair<Double, Double> geneJ = genes.get(randomJ);
		Pair<Double, Double> geneK = genes.get(randomK);
		genes.set(i, geneJ);
		genes.set(randomJ, geneK);
		genes.set(randomK, geneI);
	}

	private void swap4Genes(ArrayList<Pair<Double, Double>> genes, int i) {
		int numberOfGenes = genes.size();
		int randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomJ == i) {
			randomJ = (int) (Math.floor(numberOfGenes * Math.random()));
		}
		int randomK = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomK == i || randomK == randomJ) {
			randomK = (int) (Math.floor(numberOfGenes * Math.random()));
		}
		int randomL = (int) (Math.floor(numberOfGenes * Math.random()));
		while (randomL == i || randomL == randomJ || randomL == randomK) {
			randomL = (int) (Math.floor(numberOfGenes * Math.random()));
		}
		Pair<Double, Double> geneI = genes.get(i);
		Pair<Double, Double> geneJ = genes.get(randomJ);
		Pair<Double, Double> geneK = genes.get(randomK);
		Pair<Double, Double> geneL = genes.get(randomL);
		genes.set(i, geneJ);
		genes.set(randomJ, geneK);
		genes.set(randomK, geneL);
		genes.set(randomL, geneI);
	}
}
