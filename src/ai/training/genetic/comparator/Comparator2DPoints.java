package ai.training.genetic.comparator;

import java.util.Comparator;

import util.storage.Pair;

public class Comparator2DPoints implements Comparator<Pair<Double, Double>> {

	public Comparator2DPoints() {

	}

	/**
	 * If p1A=p2A and p1B=p2Bs, then return 0 (equal). Else, return 1 (pair1>pair2).
	 */
	public int compare(Pair<Double, Double> pair1, Pair<Double, Double> pair2) {
		if (pair1.getA().compareTo(pair2.getA()) == 0) {
			if (pair1.getB().compareTo(pair2.getB()) == 0) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
