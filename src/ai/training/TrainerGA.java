package ai.training;

import ai.training.genetic.NaturalSelection;
import ai.training.genetic.crossover.ICrossover;
import ai.training.genetic.fitness.IFitnessEvaluator;
import util.storage.Pair;

public class TrainerGA<INPUT, OUTPUT, SCORE, STATE>
		implements IGenericUnsupervisedTrainer<INPUT, OUTPUT, SCORE, STATE> {

	private static final long serialVersionUID = 1L;

	private final int generations;
	private final boolean continueUntilConvergence;
	private NaturalSelection<IGenericProblem<INPUT, OUTPUT, SCORE, STATE>> naturalSelection;

	public TrainerGA(int selectionPopulationSize, int totalPopulationSize,
			ICrossover<IGenericProblem<INPUT, OUTPUT, SCORE, STATE>> individualsCrossover,
			IFitnessEvaluator<IGenericProblem<INPUT, OUTPUT, SCORE, STATE>> fitnessEvaluator, boolean elitism,
			boolean continueUntilConvergence, int generations) {
		this.generations = generations;
		this.continueUntilConvergence = continueUntilConvergence;
		naturalSelection = new NaturalSelection<IGenericProblem<INPUT, OUTPUT, SCORE, STATE>>(selectionPopulationSize,
				totalPopulationSize, individualsCrossover, fitnessEvaluator, elitism);
	}

	public IGenericProblem<INPUT, OUTPUT, SCORE, STATE> getTrained(
			IGenericProblem<INPUT, OUTPUT, SCORE, STATE> inputProblem) {

		Pair<SCORE, OUTPUT> currentScoreOutput = inputProblem.getOutputOf(null);
		SCORE score = currentScoreOutput.getA();

		return null;
	}
}
