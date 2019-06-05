package ai.training;

import java.io.Serializable;

public interface IGenericUnsupervisedTrainer<INPUT, OUTPUT, SCORE, STATE> extends Serializable {

	public final long serialVersionUID = 1L;

	public IGenericProblem<INPUT, OUTPUT, SCORE, STATE> getTrained(
			IGenericProblem<INPUT, OUTPUT, SCORE, STATE> inputProblem);
}
