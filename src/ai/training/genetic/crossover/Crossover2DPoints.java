package ai.training.genetic.crossover;

import java.util.ArrayList;

import ai.training.genetic.Beast;
import ai.training.genetic.comparator.Comparator2DPoints;
import ai.training.genetic.mutation.IMutator;
import util.storage.Pair;

/**
 * 
 * @author Oliver
 *
 */
public class Crossover2DPoints implements ICrossover<Pair<Double, Double>> {

	public static final long serialVersionUID = 1L;

	private final IMutator<Pair<Double, Double>> mutator;

	public Crossover2DPoints(IMutator<Pair<Double, Double>> mutator) {
		this.mutator = mutator;
	}

	public Beast<Pair<Double, Double>> getCrossed(Beast<Pair<Double, Double>> individualA,
			Beast<Pair<Double, Double>> individualB) {

		ArrayList<Pair<Double, Double>> genesAoriginal = individualA.getGenes();
		ArrayList<Pair<Double, Double>> genesBoriginal = individualB.getGenes();

		int numberOfGenes = genesAoriginal.size();

		ArrayList<Pair<Double, Double>> genesA = new ArrayList<Pair<Double, Double>>(numberOfGenes);
		ArrayList<Pair<Double, Double>> genesB = new ArrayList<Pair<Double, Double>>(numberOfGenes);
		genesA.addAll(genesAoriginal);
		genesB.addAll(genesBoriginal);

		mutator.mutateGenes(genesA);
		mutator.mutateGenes(genesB);

		ArrayList<Pair<Double, Double>> newGenes = new ArrayList<Pair<Double, Double>>(numberOfGenes);
		newGenes.addAll(genesA);

		ArrayList<Integer> genesToCheck = new ArrayList<Integer>(numberOfGenes);
		for (int i = 0; i < numberOfGenes; i++) {
			genesToCheck.add(i);
		}
		while (genesToCheck.size() > 0) {
			boolean mustSwap = Math.random() < 0.5;
			int index = genesToCheck.get(0);
			Pair<Double, Double> auxGeneA = genesA.get(index);
			ArrayList<Integer> genesToRemove = new ArrayList<Integer>();
			genesToRemove.add(index);
			Pair<Double, Double> auxGeneB = genesB.get(index);
			while (auxGeneB != auxGeneA) {
				index = genesA.indexOf(auxGeneB);
				genesToRemove.add(index);
				auxGeneB = genesB.get(index);
			}
			if (mustSwap) {
				for (int id : genesToRemove) {
					newGenes.set(id, genesB.get(id));
				}
			}
			genesToCheck.removeAll(genesToRemove);
		}

		return new Beast<Pair<Double, Double>>(newGenes, new Comparator2DPoints());
	}
}
