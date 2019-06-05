package ai.training.genetic.fitness;

import java.util.ArrayList;
import java.util.HashMap;

import ai.training.genetic.Beast;
import util.storage.Pair;

public class FitnessEvaluator2DPoints implements IFitnessEvaluator<Pair<Double, Double>> {

	public static final long serialVersionUID = 1L;

	private HashMap<Beast<Pair<Double, Double>>, Double> fitnessMap;
	private HashMap<Pair<Double, Double>, HashMap<Pair<Double, Double>, Double>> distancesMap;
	private final int numberOfPoints;

	public FitnessEvaluator2DPoints(int numberOfPoints) {
		fitnessMap = new HashMap<Beast<Pair<Double, Double>>, Double>();
		distancesMap = new HashMap<Pair<Double, Double>, HashMap<Pair<Double, Double>, Double>>();
		this.numberOfPoints = numberOfPoints;
	}

	public void evaluateFitness(ArrayList<Beast<Pair<Double, Double>>> population) {
		for (Beast<Pair<Double, Double>> individual : population) {
			ArrayList<Pair<Double, Double>> individualGenes = individual.getGenes();
			Double totalDistance = 0.0;
			Pair<Double, Double> geneA = individualGenes.get(0);
			Pair<Double, Double> geneB = individualGenes.get(numberOfPoints - 1);
			HashMap<Pair<Double, Double>, Double> auxMap = distancesMap.get(geneA);
			if (auxMap == null) {
				auxMap = new HashMap<Pair<Double, Double>, Double>();
				distancesMap.put(geneA, auxMap);
			}
			Double distance = auxMap.get(geneB);
			if (distance == null) {
				distance = getDistance(geneA, geneB);
				auxMap.put(geneB, distance);
			}
			totalDistance += distance;
			for (int i = 0; i < numberOfPoints - 1; i++) {

				int j = i + 1;

				geneA = individualGenes.get(i);
				geneB = individualGenes.get(j);

				auxMap = distancesMap.get(geneA);
				if (auxMap == null) {
					auxMap = new HashMap<Pair<Double, Double>, Double>();
					distancesMap.put(geneA, auxMap);
				}
				distance = auxMap.get(geneB);
				if (distance == null) {
					distance = getDistance(geneA, geneB);
					auxMap.put(geneB, distance);
				}
				totalDistance += distance;
			}
			double totalFitness = 10000.0 / totalDistance;
			fitnessMap.put(individual, totalFitness);
		}
	}

	public double getFitness(Beast<Pair<Double, Double>> individual) {
		return fitnessMap.get(individual);
	}

	private Double getDistance(Pair<Double, Double> pointA, Pair<Double, Double> pointB) {
		double xA = pointA.getA();
		double yA = pointA.getB();
		double xB = pointB.getA();
		double yB = pointB.getB();

		double xx = xB - xA;
		double yy = yB - yA;

		return Math.sqrt(xx * xx + yy * yy);
	}
}
