package ai.training;

import java.io.Serializable;

import util.storage.Pair;

public interface IGenericProblem<INPUT, OUTPUT, SCORE, STATE> extends Serializable {

	public static final long serialVersionUID = 1L;

	public IGenericProblem<?, ?, ?, ?> getCopy();

	public Pair<SCORE, OUTPUT> getOutputOf(INPUT input);

	public STATE getState();

	public IGenericProblem<?, ?, ?, ?> setState(STATE state);
}
