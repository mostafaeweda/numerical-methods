package methods;

import items.TermIF;

public class NewtonRaphson extends EquationSolver {

	public NewtonRaphson(TermIF term, double acc) throws NotFoundRootException 
	{
		super(term, acc);
	}

	public double getNext() 
	{
		double x0 = steps.get(numberOfSteps-1);
		double x1 = x0 - (equationToSolve.evaluate(x0) / equationToSolve.differentiate().evaluate(x0));
		steps.add(x1);
		numberOfSteps ++;
		return x1;
	}

	public double solve()
	{
		// a , b are either given or evaluated by init() method
		steps.add(a);
		numberOfSteps = 1;
		while (numberOfSteps < 500)
		{
			getNext();
			if (Math.abs(equationToSolve.evaluate(steps.get(numberOfSteps-2)) - equationToSolve.evaluate(steps.get(numberOfSteps-1)))<= accuracy)
			{
				return steps.get(numberOfSteps-1);
			}
		}
		return 0;
	}
}