package ai.training;

import java.io.Serializable;
import java.util.ArrayList;

import util.storage.Pair;

public class TrainingDataset<INPUT, OUTPUT> implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Pair<INPUT, OUTPUT>> listOfPairs;

	public TrainingDataset() {
		listOfPairs = new ArrayList<Pair<INPUT, OUTPUT>>();
	}

	public void clear() {
		listOfPairs.clear();
	}

	public void addPair(INPUT input, OUTPUT output) {
		listOfPairs.add(new Pair<INPUT, OUTPUT>(input, output));
	}

	public ArrayList<Pair<INPUT, OUTPUT>> getListOfPairs() {
		return listOfPairs;
	}

	public int getListOfPairsSize() {
		return listOfPairs.size();
	}
}