/**
 * the Equation Solver class used as a general class for all solving method
 * @author Ahmed Elmorsy, Mohamed Abd El Aziz, Mohamed Yasser and Mostafa Eweda 
 */
package methods;

import items.TermIF;
import java.util.ArrayList;

public abstract class EquationSolver {

	/**
	 * the time spent to execute solving the equation
	 */
	protected long time;

	/**
	 * the value of X's in all steps of the solution process
	 */
	protected ArrayList<Double> steps;

	/**
	 * number of steps performed in the process of solution
	 */
	protected int numberOfSteps;

	/**
	 * the accuracy of the method
	 */
	protected double accuracy;

	/**
	 * the first point in the period where the root is located
	 */
	protected double a;

	/**
	 * the end of the period where the root is located
	 */
	protected double b;

	/**
	 * the equation wanted to be solve 
	 */
	protected TermIF equationToSolve;

	/**
	 * the solutions of the equation
	 */
	protected ArrayList<Double> solutions;

	/**
	 * Constructs the equation solver
	 * @param term
	 * @throws NotFoundRootException 
	 */
	public EquationSolver(TermIF term, double acc) throws NotFoundRootException
	{
		steps = new ArrayList<Double>();
		solutions = new ArrayList<Double>();
		equationToSolve = term;
		this.accuracy = acc;
		init();
	}
	
	/**
	 * Constructs the equation solver
	 * @param term
	 * @param a
	 * @param b
	 * @throws NotFoundRootException
	 */
	public EquationSolver(TermIF term, int a, int b, double acc) throws NotFoundRootException
	{
		equationToSolve = term;
		this.accuracy = acc;
		this.a = a;
		this.b = b;
	}
	
	public void setA(double a) {
		this.a = a;
	}

	public double getA() {
		return a;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getB() {
		return b;
	}
	
	public abstract double solve();

	/**
	 * This method is called when no initial points is given
	 * @throws NotFoundRootException 
	 */
	public void init() throws NotFoundRootException 
	{
		int first = 0;
		int counter = 0 ;
		double positiveFirstPoint = equationToSolve.evaluate(0);
		double negativeFirstPoint = positiveFirstPoint;
		double positiveSecondPoint;
		double negativeSecondPoint;
		boolean found = false;
		while (!found && counter < 100000)
		{
			counter ++;
			positiveSecondPoint = equationToSolve.evaluate(first + 2);
			negativeSecondPoint = equationToSolve.evaluate(-1 * (first + 2));
			if (positiveFirstPoint * positiveSecondPoint < 0)
			{
				found = true;
				a = first;
				b = first + 2;
				return;
			}
			else if (negativeFirstPoint * negativeSecondPoint < 0)
			{
				found = true;
				a = -1 * first;
				b = -1 * (first + 2);
				return;
			}
			else
			{
				positiveFirstPoint = positiveSecondPoint;
				negativeFirstPoint = negativeSecondPoint;
				first += 2;
			}
		}
		throw new NotFoundRootException();
	}

	public abstract double getNext();
}