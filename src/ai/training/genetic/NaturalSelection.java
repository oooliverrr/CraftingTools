package ai.training.genetic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ai.training.genetic.crossover.ICrossover;
import ai.training.genetic.fitness.IFitnessEvaluator;
import util.algorithms.Sorter;
import util.storage.Pair;

public class NaturalSelection<GENE> implements Serializable {

	public static final long serialVersionUID = 1L;

	private final int selectionPopulationSize, totalPopulationSize;
	private final ICrossover<GENE> individualsCrossover;
	private final IFitnessEvaluator<GENE> fitnessEvaluator;
	private final boolean elitism;

	public NaturalSelection(int selectionPopulationSize, int totalPopulationSize, ICrossover<GENE> individualsCrossover,
			IFitnessEvaluator<GENE> fitnessEvaluator, boolean elitism) {
		this.totalPopulationSize = totalPopulationSize;
		this.selectionPopulationSize = selectionPopulationSize;
		this.individualsCrossover = individualsCrossover;
		this.fitnessEvaluator = fitnessEvaluator;
		this.elitism = elitism;
	}

	public Pair<Beast<GENE>, ArrayList<Beast<GENE>>> createNewGeneration(ArrayList<Beast<GENE>> initialPopulation) {

		// Expand to populationSize
		ArrayList<Beast<GENE>> newPopulation = new ArrayList<Beast<GENE>>();
		if (elitism) {
			newPopulation.addAll(initialPopulation);
		}
		int initialPopulationSize = newPopulation.size();
		while (newPopulation.size() < totalPopulationSize) {
			boolean continueFindingChild = true;
			Beast<GENE> newChild = null;
			while (continueFindingChild) {
				int randomA = (int) (Math.floor(initialPopulationSize * Math.random()));
				int randomB = (int) (Math.floor(initialPopulationSize * Math.random()));
				Beast<GENE> parentA = initialPopulation.get(randomA);
				Beast<GENE> parentB = initialPopulation.get(randomB);

				newChild = individualsCrossover.getCrossed(parentA, parentB);

				continueFindingChild = repeatedIndividual(newChild, newPopulation);
			}

			newPopulation.add(newChild);
		}

		// Evaluate fitness and sort them by fitness
		HashMap<Beast<GENE>, Double> fitnessMap = new HashMap<Beast<GENE>, Double>();
		for (Beast<GENE> individual : newPopulation) {
			Double fitness = fitnessEvaluator.getFitness(individual);
			fitnessMap.put(individual, fitness);
		}
		LinkedHashMap<Beast<GENE>, Double> sortedFitnessMap = Sorter.sortByDescendingValue(fitnessMap);

		// Select fittest individuals
		Beast<GENE> fittestIndividual = null;
		ArrayList<Beast<GENE>> fittestIndividuals = new ArrayList<Beast<GENE>>();
		int selectedIndividuals = 0;
		for (Map.Entry<Beast<GENE>, Double> entry : sortedFitnessMap.entrySet()) {
			Beast<GENE> individual = entry.getKey();
			if (fittestIndividual == null) {
				fittestIndividual = individual;
			}
			fittestIndividuals.add(individual);
			selectedIndividuals += 1;
			if (selectedIndividuals == selectionPopulationSize) {
				break;
			}
		}

		return new Pair<Beast<GENE>, ArrayList<Beast<GENE>>>(fittestIndividual, fittestIndividuals);
	}

	private boolean repeatedIndividual(Beast<GENE> individual, ArrayList<Beast<GENE>> population) {
		for (Beast<GENE> individualInPopulation : population) {
			if (individual.similarTo(individualInPopulation)) {
				return true;
			}
		}
		return false;
	}
}
