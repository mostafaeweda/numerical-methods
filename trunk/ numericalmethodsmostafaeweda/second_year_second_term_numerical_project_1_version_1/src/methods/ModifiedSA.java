package methods;

import items.TermIF;

/**
 * represents the Modified successive approximation for solving the equation
 * @see SuccessiveApprox
 * @see EquationSolver
 * @since JDK 1.6
 * @version 1.0
 */
public class ModifiedSA extends SuccessiveApprox {

	public ModifiedSA(TermIF equation, double acc) throws NotFoundRootException {
		super(equation, acc);
	}

	public ModifiedSA(TermIF equation, double acc, double a, double b) throws NotFoundRootException {
		super(equation, acc, a, b);
	}

	/**
	 * @see SuccessiveApprox#getNext()
	 * differs in calculating the step with the alpha
	 */
	@Override
	public double getNext() {
		double a = steps.get(steps.size() -1);
		double b = steps.get(steps.size() -2);
		double f_ebsay = (smallFX.evaluate(b)-smallFX.evaluate(a)) / (b - a);
		double alpha = 1 / (1-f_ebsay);
		return b + alpha * (smallFX.evaluate(b) - b);
	}
}
