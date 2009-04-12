package methods;

import items.TermIF;

/**
 * represents the Bisection method for solving the equation
 * @see EquationSolver
 * @since JDK 1.6
 * @version 1.0
 */
public class Bisection extends EquationSolver {

	public Bisection(TermIF equation, double acc) throws NotFoundRootException {
		super(equation, acc);
	}

	public Bisection(TermIF equation, double acc, double a, double b) throws NotFoundRootException {
		super(equation, acc);
		this.a = a;
		this.b = b;
	}

	/**
	 * iterates to cut the region by half eliminating half of the interval
	 */
	public double solve() {
		long before = System.nanoTime();
		double w = 0; 
		steps.add(a);
		steps.add(b);
		while (Math.abs(a - b) >= accuracy) { // || Math.abs(a- b) >= accuracy
			w = getNext();
			if (equationToSolve.evaluate(w) * equationToSolve.evaluate(a) < 0) {
				b = w;
			} else {
				a = w;
			}
			numberOfSteps++;
			steps.add(w);
		}
		Double result = new Double(w);
		time = System.nanoTime() - before;
		System.out.println(result);
		return result;
	}

	/**
	 * gets the middle of the current region
	 */
	@Override
	public double getNext() {
		return (a + b) / 2;
	}
}
