package ai.neuralnets.links;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import ai.neuralnets.neurons.IGenericNeuron;
import util.storage.Pair;

public class Link implements Serializable {

	private static final long serialVersionUID = 1L;

	private double weight;
	private double weightDerivative;
	private ArrayList<Pair<IGenericNeuron, IGenericNeuron>> neuronPairs;

	public Link(boolean beginAtRandom) {
		if (beginAtRandom) {
			weight = -1.0 + 2.0 * Math.random();
			// weight = 1.0;
		} else {
			weight = 1.0;
		}
		weightDerivative = 0.0;

		neuronPairs = new ArrayList<Pair<IGenericNeuron, IGenericNeuron>>();
	}

	@Override
	public String toString() {
		return "[Link, weight = " + weight + ", Pairs = " + Arrays.toString(neuronPairs.toArray()) + "]";
	}

	public void addPairOfNeurons(Pair<IGenericNeuron, IGenericNeuron> pair) {
		neuronPairs.add(pair);
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeightDerivative() {
		return weightDerivative;
	}

	public void setWeightDerivative(double weightDerivative) {
		this.weightDerivative = weightDerivative;
	}

	public void addWeightDerivative(double weightDerivative) {
		this.weightDerivative = this.weightDerivative + weightDerivative;
	}

	public void activateDerivative(double learningRate) {
		setWeight(weight - learningRate * weightDerivative);
	}

	public ArrayList<Pair<IGenericNeuron, IGenericNeuron>> getPairsOfNeurons() {
		return neuronPairs;
	}

	public void transmit() {
		neuronPairs.stream().forEach((pair) -> {
			IGenericNeuron inputNeuron = pair.getA();
			IGenericNeuron outputNeuron = pair.getB();
			outputNeuron.addIncomeInformation(weight, inputNeuron.getOutput());
		});
	}

	public void transmitDelta() {
		neuronPairs.stream().forEach((pair) -> {
			IGenericNeuron inputNeuron = pair.getA();
			IGenericNeuron outputNeuron = pair.getB();
			inputNeuron.addDelta(inputNeuron.getDerivative() * weight * outputNeuron.getDelta());
		});
	}
}
